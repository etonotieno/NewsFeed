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

package com.edoubletech.newsfeed.newsapi.models

import com.google.gson.annotations.SerializedName

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
        @field:SerializedName("publishedAt") val publicationTime: String
)

data class NewsApiSource(
        @field:SerializedName("id") val id: String,
        @field:SerializedName("name") val name: String
)
