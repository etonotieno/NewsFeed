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

package com.edoubletech.newsfeed.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.edoubletech.newsfeed.data.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news_table WHERE mSectionName =:sectionName ORDER BY mPublicationDate")
    DataSource.Factory<Integer, News> getNewsList(String sectionName);

    @Query("SELECT * FROM news_table WHERE mId =:id ")
    LiveData<News> loadNewsById(String id);

    @Insert
    void addNews(List<News> news);

    @Delete
    void deleteNews(News news);

}
