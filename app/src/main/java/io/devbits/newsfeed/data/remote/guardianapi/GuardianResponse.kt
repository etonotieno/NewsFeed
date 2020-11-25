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

package io.devbits.newsfeed.data.remote.guardianapi

import com.google.gson.annotations.SerializedName

class GuardianResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("userTier") val userTier: String,
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("startIndex") val startIndex: Int,
    @field:SerializedName("pageSize") val pageSize: Int,
    @field:SerializedName("currentPage") val currentPage: Int,
    @field:SerializedName("pages") val pages: Int,
    @field:SerializedName("orderBy") val orderBy: String,
    @field:SerializedName("results") val results: List<GuardianResult>
)