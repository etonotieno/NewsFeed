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

import androidx.room.Room
import io.devbits.newsfeed.cache.db.NewsDatabase
import io.devbits.newsfeed.data.NewsDataRepository
import io.devbits.newsfeed.data.repository.NewsRepository
import io.devbits.newsfeed.data.usecase.GetNews
import io.devbits.newsfeed.guardianapi.data.GuardianApiService
import io.devbits.newsfeed.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module(override = true) {
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "news.db"
        ).build()
    }
    factory { get<NewsDatabase>().cachedNewsDao() }

    factory<NewsRepository> { NewsDataRepository() }

    factory { GetNews(get()) }
    single { GuardianApiService() }

    viewModel { MainViewModel(get()) }
}
