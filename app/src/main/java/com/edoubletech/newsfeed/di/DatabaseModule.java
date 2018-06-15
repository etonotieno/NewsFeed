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

import android.arch.persistence.room.Room;
import android.content.Context;

import com.edoubletech.newsfeed.data.NewsDatabase;
import com.edoubletech.newsfeed.data.Repository;
import com.edoubletech.newsfeed.data.dao.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private Context mContext;

    public DatabaseModule(Context context){
        mContext = context;

    }

    @Singleton
    @Provides
    Repository provideNewsRepository(){
        return new Repository();
    }

    @Singleton
    @Provides
    NewsDatabase provideNewsDb(){
        return Room.databaseBuilder(mContext.getApplicationContext(), NewsDatabase.class,
                "news.db")
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    NewsDao provideNewsDao(NewsDatabase db){
        return db.newsDao();
    }
}
