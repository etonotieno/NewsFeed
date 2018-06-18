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

package com.edoubletech.newsfeed.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by EtonOtieno on 3/21/2018
 */

@Entity(tableName = "news_table")
public class News {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int mId;
    private String mImageUrl;
    private String mWebUrl;
    private String mSectionName;
    private String mTitle;
    private String mTrailText;
    private String mBodyText;
    private String mPublicationDate;

    public News(String imageUrl, String webUrl, String sectionName, String title,
                String trailText, String bodyText, String publicationDate) {
        this.mImageUrl = imageUrl;
        this.mWebUrl = webUrl;
        this.mSectionName = sectionName;
        this.mTitle = title;
        this.mTrailText = trailText;
        this.mBodyText = bodyText;
        this.mPublicationDate = publicationDate;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTrailText() {
        return mTrailText;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getBodyText() {
        return mBodyText;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }
}
