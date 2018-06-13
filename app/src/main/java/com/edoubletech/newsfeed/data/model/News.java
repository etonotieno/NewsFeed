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

package com.edoubletech.newsfeed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EtonOtieno on 3/21/2018
 */

public class News implements Parcelable {
    
    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }
        
        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
    
    private String mId;
    private String mImageUrl;
    private String mWebUrl;
    private String mSectionName;
    private String mTitle;
    private String mTrailText;
    private String mBodyText;
    private String mPublicationDate;
    
    public News(String id, String imageUrl, String webUrl, String sectionName, String title,
                String trailText, String bodyText, String publicationDate) {
        this.mId = id;
        this.mImageUrl = imageUrl;
        this.mWebUrl = webUrl;
        this.mSectionName = sectionName;
        this.mTitle = title;
        this.mTrailText = trailText;
        this.mBodyText = bodyText;
        this.mPublicationDate = publicationDate;
    }
    
    private News(Parcel in) {
        mId = in.readString();
        mImageUrl = in.readString();
        mWebUrl = in.readString();
        mSectionName = in.readString();
        mTitle = in.readString();
        mTrailText = in.readString();
        mBodyText = in.readString();
        mPublicationDate = in.readString();
    }
    
    public String getId() {
        return mId;
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
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mImageUrl);
        dest.writeString(mWebUrl);
        dest.writeString(mSectionName);
        dest.writeString(mTitle);
        dest.writeString(mTrailText);
        dest.writeString(mBodyText);
        dest.writeString(mPublicationDate);
    }
}
