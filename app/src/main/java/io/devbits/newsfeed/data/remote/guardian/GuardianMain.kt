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

package io.devbits.newsfeed.data.remote.guardian

import com.google.gson.annotations.SerializedName
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.Origin

class GuardianMain(@field:SerializedName("response") val response: GuardianResponse)

fun GuardianMain.mapToNews(): List<News> {
    val results = this.response.results
    return results.map { result ->
        News(
            id = result.id,
            imageUrl = result.fields.thumbnail,
            webUrl = result.webUrl,
            sectionName = result.sectionName,
            title = result.webTitle,
            body = result.fields.htmlBody,
            publicationDate = result.webPublicationDate,
            source = result.fields.publication,
            summary = result.fields.trailText,
            origin = Origin.GUARDIAN_API
        )
    }
}