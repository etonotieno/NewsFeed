/*
 *  Copyright 2019 Eton Otieno
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

package io.devbits.newsfeed.data.remote

import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.Result
import io.devbits.newsfeed.data.remote.guardian.GuardianApiService
import io.devbits.newsfeed.data.remote.guardian.mapToNews
import io.devbits.newsfeed.data.remote.news.NewsApiService
import io.devbits.newsfeed.data.remote.news.mapToNews
import io.devbits.newsfeed.data.source.NewsDataSource

class NewsRemoteDataSource(
    private val guardianApiService: GuardianApiService,
    private val newsApiService: NewsApiService
) : NewsDataSource {

    override suspend fun getNewsResults(): Result<List<News>> {
        return try {
            Result.Loading
            val guardianApiArticles = guardianApiService.getNewsResponse("technology")
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
        TODO("This functionality is not supported for a remote data source")
    }
}