/*
 *  Copyright (C) 2018 Eton Otieno Oboch
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
 *
 */

package com.edoubletech.newsfeed.data.guardian

import com.edoubletech.newsfeed.data.model.News
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GuardianMain(@SerializedName("response") @Expose val response: GuardianResponse)

fun GuardianMain.mapToNews(): List<News> {
    val response = this.response
    val results = response.results
    val articles = ArrayList<News>()
    for (result in results) {
        articles.add(News(
                result.id,
                result.fields.thumbnail, /* Thumbnail for the news */
                result.webUrl, /* Website url*/
                result.sectionName, /* Section name*/
                result.webTitle, /* Web Title of Article*/
                result.fields.trailText, /* Trail Text*/
                result.fields.bodyText, /* Description */
                result.webPublicationDate)) /* Publication Date*/
    }
    return articles
}