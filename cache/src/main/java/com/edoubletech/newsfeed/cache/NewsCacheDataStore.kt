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

package com.edoubletech.newsfeed.cache

import com.edoubletech.newsfeed.cache.db.NewsDatabase
import com.edoubletech.newsfeed.cache.model.NewsDbModel
import com.edoubletech.newsfeed.data.data.NewsDataStore
import com.edoubletech.newsfeed.data.model.News

private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

class NewsCacheDataStore(
    private val newsDatabase: NewsDatabase,
    private val preferencesHelper: PreferencesHelper
) : NewsDataStore {

    override suspend fun getObservableNews(): List<News> {
        return newsDatabase.cachedNewsDao().getListOfNews().map {
            News(
                id = it.id,
                imageUrl = it.imageUrl,
                webUrl = it.webUrl,
                sectionName = it.sectionName,
                title = it.title,
                bodyText = it.bodyText,
                publicationDate = it.publicationDate
            )
        }
    }

    override fun saveNews(news: List<News>) {
        newsDatabase.cachedNewsDao().insertNews(news.map {
            NewsDbModel(
                id = it.id,
                imageUrl = it.imageUrl,
                webUrl = it.webUrl,
                sectionName = it.sectionName,
                title = it.title,
                bodyText = it.bodyText,
                publicationDate = it.publicationDate
            )
        })
    }

    override fun clearListOfNews() {
        newsDatabase.cachedNewsDao().clearListOfNews()
    }

    override fun isCached(): Boolean = newsDatabase.cachedNewsDao().getListOfNews().isNotEmpty()


    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}