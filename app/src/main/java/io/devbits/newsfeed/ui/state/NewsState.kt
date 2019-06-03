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

package io.devbits.newsfeed.ui.state

import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.ui.state.NewsState.Error
import io.devbits.newsfeed.ui.state.NewsState.Loading
import io.devbits.newsfeed.ui.state.NewsState.Success

/**
 * This class holds the View state for the app using a sealed class.
 * There are 3 states:
 * [Loading], [Success] and [Error]
 */
sealed class NewsState(
    resourceState: ResourceState,
    val data: List<News>? = null,
    val error: String? = null
) {
    object Loading : NewsState(ResourceState.LOADING)
    data class Success(private val newsList: List<News>) : NewsState(ResourceState.SUCCESS, newsList)
    data class Error(private val message: String) : NewsState(ResourceState.ERROR, error = message)
}

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}