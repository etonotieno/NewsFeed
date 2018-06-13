/*
 *   Copyright (C) 2018 Eton Otieno Oboch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.edoubletech.newsfeed.di;

import com.edoubletech.newsfeed.NewsFeed;
import com.edoubletech.newsfeed.view.category.CategoryFragment;
import com.edoubletech.newsfeed.view.main.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NewsModule.class)
public interface NewsComponent {

    void inject(NewsFeed application);

    void inject(CategoryFragment categoryFragment);

    void inject(MainFragment mainFragment);
}
