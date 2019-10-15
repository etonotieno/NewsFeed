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

import dagger.Binds
import dagger.Module
import io.devbits.newsfeed.data.news.LocalNewsSource
import io.devbits.newsfeed.data.news.NewsRepositoryImpl
import io.devbits.newsfeed.data.news.NewsSource
import io.devbits.newsfeed.data.news.RemoteNewsSource
import io.devbits.newsfeed.domain.getnews.NewsRepository

@Module
abstract class DataModule {

    @Binds
    @DataSource(source = Source.REMOTE)
    abstract fun bindRemoteNewsSource(remoteNewsSource: RemoteNewsSource): NewsSource

    @Binds
    @DataSource(source = Source.LOCAL)
    abstract fun bindLocalNewsSource(localDataSource: LocalNewsSource): NewsSource

    @Binds
    abstract fun bindRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}