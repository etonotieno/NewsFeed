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

package io.devbits.newsfeed.data

import io.devbits.newsfeed.api.guardian.GuardianApiService
import io.devbits.newsfeed.api.guardian.model.mapToNews
import io.devbits.newsfeed.api.news.NewsApiService
import io.devbits.newsfeed.ui.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class NewsRepository(
    private val guardianApiService: GuardianApiService,
    private val newsApiService: NewsApiService
) {

    fun guardianApiFlow(): Flow<Result<List<News>>> = channelFlow {
        send(Result.Loading)
        try {
            val result = guardianApiService.getNewsResponseAsync("technology")
                .mapToNews()
                .sortedByDescending { it.publicationDate }

            send(Result.Success(result))
        } catch (e: Exception) {
            send(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}