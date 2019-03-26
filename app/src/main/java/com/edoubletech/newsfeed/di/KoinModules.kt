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

package com.edoubletech.newsfeed.di

import androidx.room.Room
import com.edoubletech.newsfeed.cache.NewsCacheDataStore
import com.edoubletech.newsfeed.cache.PreferencesHelper
import com.edoubletech.newsfeed.cache.db.NewsDatabase
import com.edoubletech.newsfeed.data.NewsDataRepository
import com.edoubletech.newsfeed.data.data.NewsDataStore
import com.edoubletech.newsfeed.data.data.NewsDataStoreFactory
import com.edoubletech.newsfeed.data.repository.NewsRepository
import com.edoubletech.newsfeed.data.usecase.GetNews
import com.edoubletech.newsfeed.guardian.GuardianApiDataStore
import com.edoubletech.newsfeed.guardian.GuardianApiService
import com.edoubletech.newsfeed.newsapi.NewsApiDataStore
import com.edoubletech.newsfeed.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module(override = true) {

    single { PreferencesHelper(androidContext()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "news.db"
        ).build()
    }
    factory { get<NewsDatabase>().cachedNewsDao() }

    factory<NewsDataStore>("cache") { NewsCacheDataStore(get(), get()) }
    factory<NewsDataStore>("guardian") { GuardianApiDataStore(get()) }
    factory<NewsDataStore>("news-api") { NewsApiDataStore(get()) }
    factory { NewsDataStoreFactory(get("cache"), get("news-api"), get("guardian")) }

    factory<NewsRepository> { NewsDataRepository(get()) }
    factory { GetNews(get()) }
    single { GuardianApiService() }
    viewModel { MainViewModel(get()) }
}
