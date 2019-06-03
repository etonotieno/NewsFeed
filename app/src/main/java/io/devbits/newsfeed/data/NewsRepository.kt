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
import io.devbits.newsfeed.api.news.model.mapToNews
import io.devbits.newsfeed.ui.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    private val guardianApiService: GuardianApiService,
    private val newsApiService: NewsApiService
) {

    suspend fun getListOfNews(): Result<List<News>> = withContext(Dispatchers.IO) {
        Result.Loading
        try {
            val guardianNews = guardianApiService.getNewsResponseAsync("technology").await().mapToNews()
            val news = newsApiService.getNewsResponseAsync().await().mapToNews()
            val merged = news.plus(guardianNews)
                .sortedBy {
                    it.publicationDate
                }
            Result.Success(merged)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}