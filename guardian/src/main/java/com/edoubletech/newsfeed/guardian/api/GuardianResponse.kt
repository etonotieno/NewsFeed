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

package com.edoubletech.newsfeed.guardian.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class GuardianResponse(
        @SerializedName("status") @Expose val status: String,
        @SerializedName("userTier") @Expose val userTier: String,
        @SerializedName("total") @Expose val total: Int,
        @SerializedName("startIndex") @Expose val startIndex: Int,
        @SerializedName("pageSize") @Expose val pageSize: Int,
        @SerializedName("currentPage") @Expose val currentPage: Int,
        @SerializedName("pages") @Expose val pages: Int,
        @SerializedName("orderBy") @Expose val orderBy: String,
        @SerializedName("results") @Expose val results: List<GuardianResult> = ArrayList()
)