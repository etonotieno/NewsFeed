/*
 *  Copyright (C) 2019 Eton Otieno
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.devbits.newsfeed.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.NewsRepository
import io.devbits.newsfeed.data.Result
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsLiveData = MutableLiveData<Result<List<News>>>()
    val newsLiveData: LiveData<Result<List<News>>>
        get() = _newsLiveData

    init {
        viewModelScope.launch {
            val guardianNews = newsRepository.getGuardianNews()
            _newsLiveData.postValue(guardianNews)
        }
    }

}