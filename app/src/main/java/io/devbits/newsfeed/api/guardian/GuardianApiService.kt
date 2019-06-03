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

package io.devbits.newsfeed.api.guardian

import io.devbits.newsfeed.api.guardian.model.GuardianMain
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by EtonOtieno on 3/2/2018
 */

interface GuardianApiService {

    @GET("search")
    fun getNewsResponseAsync(
        @Query("page-size") pageSize: String = "50",
        @Query("api-key") apiKey: String = "",
        @Query("section") category: String?,
        @Query("show-fields") fields: String = "all",
        @Query("format") format: String = "json"
    ): Deferred<GuardianMain>

    companion object {
        operator fun invoke(): GuardianApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(GUARDIAN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create()
        }
    }
}

private const val GUARDIAN_BASE_URL = "https://content.guardianapis.com/"