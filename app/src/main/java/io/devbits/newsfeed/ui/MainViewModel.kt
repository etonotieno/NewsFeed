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

package io.devbits.newsfeed.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.NewsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combineLatest
import kotlinx.coroutines.launch

/**
 * This is the MainViewModel that contains the newsLiveData needed in the app.
 */
class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<News>>()
    val newsLiveData: LiveData<List<News>>
        get() = _newsLiveData

    init {
        viewModelScope.launch {
            repository.getNewsApiResult()
                .combineLatest(repository.getGuardianApiResult()) { newsApiResults, guardianResults ->
                    newsApiResults.plus(guardianResults)
                        .sortedByDescending {
                            it.publicationDate
                        }
                }.collect {
                    _newsLiveData.value = it
                }
        }
    }

}