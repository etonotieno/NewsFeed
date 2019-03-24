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

package com.edoubletech.newsfeed.newsapi

import com.edoubletech.newsfeed.data.data.NewsDataStore
import com.edoubletech.newsfeed.data.model.News
import com.edoubletech.newsfeed.newsapi.response.mapToNews

class NewsApiDataStore(private val newsApiService: NewsApiService) : NewsDataStore {
    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearListOfNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // TODO: Figure out mechanism to pass news category here
    override suspend fun getObservableNews(): List<News> {
        val response = newsApiService.getNews()
        return response.mapToNews()
    }

    override fun saveNews(news: List<News>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

