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

package com.edoubletech.newsfeed.data.networking

import com.edoubletech.newsfeed.BuildConfig
import com.edoubletech.newsfeed.guardian.GuardianMain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by EtonOtieno on 3/2/2018
 */

interface Service {

    @GET("search")
    suspend fun getNewsAsync(
            @Query("page-size") pageSize: String = "50",
            @Query("api-key") apiKey: String = BuildConfig.GUARDIAN_API_KEY,
            @Query("section") section: String,
            @Query("show-fields") fields: String = "all",
            @Query("format") format: String = "json"
    ): Response<GuardianMain>
}
