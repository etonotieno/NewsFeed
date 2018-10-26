/*
 *   Copyright (C) 2018 Eton Otieno Oboch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.edoubletech.newsfeed.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.edoubletech.newsfeed.BuildConfig
import com.edoubletech.newsfeed.data.networking.Injector
import com.edoubletech.newsfeed.data.networking.Service
import com.edoubletech.newsfeed.guardian.GuardianMain
import com.edoubletech.newsfeed.guardian.mapToNews
import com.edoubletech.newsfeed.ui.NewsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

object Repository {

    private val newsLiveData = MutableLiveData<NewsState>()
    private val categoryLiveData = MutableLiveData<String>()

    val news: LiveData<NewsState> = Transformations.switchMap(categoryLiveData) {
        getNewsData(it)
    }

    fun search(categoryName: String) {
        categoryLiveData.value = categoryName
    }

    private fun getNewsData(categoryName: String): LiveData<NewsState> {
        newsLiveData.postValue(NewsState.Loading)

        val service = Injector.provideRetrofit().create(Service::class.java)
        val call = service.getNews("50", BuildConfig.GUARDIAN_API_KEY,
                categoryName, "all", "json")

        // Make the actual call. This is an asynchronous call.
        call.enqueue(object : Callback<GuardianMain> {
            override fun onResponse(call: Call<GuardianMain>, response: Response<GuardianMain>) {
                if (response.isSuccessful) {
                    Timber.d(" NewsResponse is successful")
                    response.body()?.mapToNews()?.let {
                        newsLiveData.postValue(NewsState.Success(it))
                    }
                } else {
                    val statusCode = response.code()
                    val errorBody = response.errorBody()
                    Timber.i("Network Error: " + errorBody?.string()
                            + "\nStatus Code: " + statusCode)
                    newsLiveData.postValue(NewsState.Error(errorBody?.string()))

                }
            }

            override fun onFailure(call1: Call<GuardianMain>, throwable: Throwable) {
                newsLiveData.postValue(NewsState.Error(throwable.message))
            }
        })
        return newsLiveData
    }
}
