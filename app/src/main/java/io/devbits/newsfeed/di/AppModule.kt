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

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.devbits.newsfeed.BuildConfig
import io.devbits.newsfeed.data.remote.guardianapi.GuardianApiService
import io.devbits.newsfeed.data.remote.newsapi.NewsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DataModule::class, ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideOkhttp(context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BASIC
        }

        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .alwaysReadResponseBody(true)
            .apply { if (!BuildConfig.DEBUG) redactHeaders("X-Api-Key") }
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
    }

    @Provides
    @Singleton
    @Named(GUARDIAN_API)
    fun provideGuardianApiRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(GUARDIAN_BASE_URL).build()
    }

    @Provides
    @Singleton
    @Named(NEWS_API)
    fun provideNewsApiRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(NEWS_API_BASE_URL).build()
    }

    @Provides
    @Singleton
    fun provideGuardianApiService(@Named(GUARDIAN_API) retrofit: Retrofit): GuardianApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(@Named(NEWS_API) retrofit: Retrofit): NewsApiService {
        return retrofit.create()
    }

    companion object {
        private const val NEWS_API = "news-api"
        private const val GUARDIAN_API = "guardian-api"
        private const val NEWS_API_BASE_URL = "https://newsapi.org/"
        private const val GUARDIAN_BASE_URL = "https://content.guardianapis.com/"
    }
}
