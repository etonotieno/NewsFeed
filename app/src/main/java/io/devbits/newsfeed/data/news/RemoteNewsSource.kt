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

package io.devbits.newsfeed.data.news

import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.Result
import io.devbits.newsfeed.data.remote.guardianapi.GuardianApiService
import io.devbits.newsfeed.data.remote.guardianapi.mapToNews
import io.devbits.newsfeed.data.remote.newsapi.NewsApiService
import io.devbits.newsfeed.data.remote.newsapi.mapToNews
import javax.inject.Inject

class RemoteNewsSource @Inject constructor(
    private val guardianApiService: GuardianApiService,
    private val newsApiService: NewsApiService
) : NewsSource {

    override suspend fun getNewsResults(section: String): Result<List<News>> {
        return try {
            Result.Loading
            val guardianApiArticles = guardianApiService.getNewsResponse(section)
                .mapToNews()
            val newsApiArticles = newsApiService.getNewsResponse()
                .mapToNews()

            val finalList = guardianApiArticles.union(newsApiArticles)
                .sortedByDescending { it.publicationDate }

            Result.Success(finalList)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    // This functionality is not supported for a remote data source
    override suspend fun getNewsById(newsId: String): Result<News> {
        throw IllegalStateException("getNewsById is not supported for a remote NewsSource")
    }

}