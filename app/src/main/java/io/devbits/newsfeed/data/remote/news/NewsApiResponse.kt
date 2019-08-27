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

package io.devbits.newsfeed.data.remote.news

import com.google.gson.annotations.SerializedName
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.Origin

data class NewsApiResponse(
    @field:SerializedName("articles") val articles: List<NewsApiArticle>
)

data class NewsApiArticle(
    @field:SerializedName("source") val source: NewsApiSource,
    @field:SerializedName("author") val author: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("urlToImage") val imageUrl: String,
    @field:SerializedName("publishedAt") val publicationTime: String,
    @field:SerializedName("content") val content: String
)

data class NewsApiSource(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String
)

fun NewsApiResponse.mapToNews(): List<News> {
    val newsArticles = this.articles
    val articles = mutableListOf<News>()
    for (article in newsArticles) {
        articles.add(
            News(
                id = article.url,
                imageUrl = article.imageUrl,
                webUrl = article.url,
                // TODO: Find out how to add a section for the News API
                sectionName = article.source.name,
                title = article.title,
                // TODO: Launch a webview instead of a Details screen since this is truncated
                body = article.content,
                publicationDate = article.publicationTime,
                source = article.source.name,
                summary = article.description,
                origin = Origin.NEWS_API
            )
        )
    }
    return articles
}