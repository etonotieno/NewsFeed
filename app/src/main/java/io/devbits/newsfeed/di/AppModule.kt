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

package io.devbits.newsfeed.di

import dagger.Module
import dagger.Provides
import io.devbits.newsfeed.data.remote.guardianapi.GuardianApiService
import io.devbits.newsfeed.data.remote.newsapi.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [DataModule::class, ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideGuardianApiService(builder: Retrofit.Builder): GuardianApiService {
        return builder
            .baseUrl(Companion.GUARDIAN_BASE_URL)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(builder: Retrofit.Builder): NewsApiService {
        return builder
            .baseUrl(Companion.NEWS_API_BASE_URL)
            .build()
            .create()
    }

    companion object {
        private const val NEWS_API_BASE_URL = "https://newsapi.org/"
        private const val GUARDIAN_BASE_URL = "https://content.guardianapis.com/"
    }
}
