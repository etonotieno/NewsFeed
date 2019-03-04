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

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.edoubletech.newsfeed.data.networking.Service
import com.edoubletech.newsfeed.guardian.GuardianMain
import com.edoubletech.newsfeed.guardian.mapToNews
import com.edoubletech.newsfeed.ui.state.NewsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * This class handles all the data loading logic needed for the app and does it with the use of
 * coroutines.
 */
class Repository(private val service: Service) {

    private val newsLiveData = MutableLiveData<NewsState>()
    private val categoryLiveData = MutableLiveData<String>()

    private lateinit var response: Response<GuardianMain>

    fun getNews(): LiveData<NewsState> = categoryLiveData.switchMap(Function<String, LiveData<NewsState>> {
        newsLiveData
    })

    suspend fun fetchNews() {
        response = service.getNewsAsync(category = categoryLiveData.value)
        newsLiveData.postValue(NewsState.Loading)
        withContext(Dispatchers.IO) {
            if (response.isSuccessful) {
                response.body()?.mapToNews()?.let {
                    newsLiveData.postValue(NewsState.Success(it))
                }
            } else {
                val errorBody = response.errorBody()
                newsLiveData.postValue(NewsState.Error(errorBody?.string().toString()))
            }
        }
    }

    fun setCategory(category: String) {
        categoryLiveData.value = category
    }
}

private fun <I, O> LiveData<I>.switchMap(function: Function<I, LiveData<O>>): LiveData<O> =
        Transformations.switchMap(this, function)