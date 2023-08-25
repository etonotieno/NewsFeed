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

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.devbits.newsfeed.ui.MainActivity
import io.devbits.newsfeed.ui.home.HomeFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ContextModule::class, AppContextModule::class])
interface AppComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun app(@BindsInstance app: Application): Builder

        fun appModule(module: AppContextModule): Builder

        fun build(): AppComponent
    }
}
