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

package io.devbits.newsfeed.data.repository

import io.devbits.newsfeed.data.model.News
import io.devbits.newsfeed.data.model.Result
import io.devbits.newsfeed.di.DataSource
import io.devbits.newsfeed.di.Source
import io.devbits.newsfeed.domain.getnews.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    @DataSource(Source.REMOTE) private val remoteNewsSource: NewsSource,
    @DataSource(Source.LOCAL) private val localNewsSource: NewsSource
) : NewsRepository {

    override suspend fun getNewsResults(section: String): Result<List<News>> {
        return remoteNewsSource.getNewsResults(section)
    }

    override suspend fun getNewsById(newsId: String): Result<News> {
        return localNewsSource.getNewsById(newsId)
    }

}
