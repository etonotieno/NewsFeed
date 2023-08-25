/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.devbits.newsfeed.data.remote.guardianapi.model

import com.google.gson.annotations.SerializedName

class GuardianResult(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("sectionId") val sectionId: String,
    @field:SerializedName("webTitle") val webTitle: String,
    @field:SerializedName("webPublicationDate") val webPublicationDate: String,
    @field:SerializedName("fields") val fields: GuardianFields,
    @field:SerializedName("webUrl") val webUrl: String,
    @field:SerializedName("apiUrl") val apiUrl: String,
    @field:SerializedName("sectionName") val sectionName: String
)
