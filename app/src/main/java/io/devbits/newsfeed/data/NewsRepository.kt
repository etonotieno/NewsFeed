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
import io.devbits.newsfeed.api.news.NewsApiService
import io.devbits.newsfeed.api.news.model.mapToNews
import io.devbits.newsfeed.ui.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val guardianApiService: GuardianApiService,
    private val newsApiService: NewsApiService
) {

    fun getListOfNews(): NewsResultFlow = NewsResultFlow()

    inner class NewsResultFlow : AbstractFlow<Result<List<News>>>() {

        override suspend fun collectSafely(collector: FlowCollector<Result<List<News>>>) {
            collector.emit(Result.Loading)
            try {
//                val guardianNews = guardianApiService.getNewsResponseAsync("technology")
//                    .map {
//                        it.mapToNews()
//                    }
//                    .buffer(Channel.CONFLATED)
//                    .flowOn(Dispatchers.IO)

                newsApiService.getNewsResponseAsync()
                    .map {
                        it.mapToNews()
                    }
                    .buffer(Channel.CONFLATED)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        collector.emit(Result.Success(it))
                    }
            } catch (e: Exception) {
                collector.emit(Result.Error(e))
            }
        }

    }

}