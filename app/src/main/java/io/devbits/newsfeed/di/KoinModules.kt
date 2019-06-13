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

import io.devbits.newsfeed.FlowCallAdapterFactory
import io.devbits.newsfeed.api.guardian.GuardianApiService
import io.devbits.newsfeed.api.news.NewsApiService
import io.devbits.newsfeed.data.NewsRepository
import io.devbits.newsfeed.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val GUARDIAN_BASE_URL = "https://content.guardianapis.com/"
private const val NEWS_API_BASE_URL = "https://newsapi.org/v2/"

val appModule = module {
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }
    single<GuardianApiService> {
        get<Retrofit.Builder>()
            .baseUrl(GUARDIAN_BASE_URL)
            .build()
            .create()
    }
    single<NewsApiService> {
        get<Retrofit.Builder>()
            .baseUrl(NEWS_API_BASE_URL)
            .build()
            .create()
    }
    single { NewsRepository(get(), get()) }
    viewModel { MainViewModel(get()) }
}
