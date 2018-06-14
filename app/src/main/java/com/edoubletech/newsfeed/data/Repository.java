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

package com.edoubletech.newsfeed.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.edoubletech.newsfeed.NewsFeed;
import com.edoubletech.newsfeed.data.dao.NewsDao;
import com.edoubletech.newsfeed.data.model.News;

public class Repository {

    private final int DATABASE_PAGE_SIZE = 20;

    public LiveData<PagedList<News>> getNewsList(String categoryName) {
        NewsDao dao = NewsFeed.getNewsComponent().exposeDao();
        NewsBoundaryCallback callback = new NewsBoundaryCallback(categoryName);
        DataSource.Factory<Integer, News> dataFactory = dao.getNewsList(categoryName);
        LivePagedListBuilder<Integer, News> data = new LivePagedListBuilder<>(dataFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(callback);
        return data.build();

    }
}
