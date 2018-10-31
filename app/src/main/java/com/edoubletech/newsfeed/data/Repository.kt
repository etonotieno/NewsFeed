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
import com.edoubletech.newsfeed.guardian.mapToNews
import com.edoubletech.newsfeed.ui.NewsState
import kotlinx.coroutines.*

class Repository(private val categoryName: String) {

    private val newsLiveData = MutableLiveData<NewsState>()

    suspend fun loadData() {
        newsLiveData.postValue(NewsState.Loading)

        val service = Injector.provideRetrofit().create(Service::class.java)
        val call = service.getNews("50", BuildConfig.GUARDIAN_API_KEY,
                categoryName, "all", "json")

        withContext(Dispatchers.IO) {
            val response = call.await()
            if (response.isSuccessful) {
                response.body()?.mapToNews()?.let {
                    newsLiveData.postValue(NewsState.Success(it))
                }
            } else {
                val errorBody = response.errorBody()
                newsLiveData.postValue(NewsState.Error(errorBody?.string()))
            }
        }
    }

    fun getNewsData(): LiveData<NewsState> {
        return newsLiveData
    }
}