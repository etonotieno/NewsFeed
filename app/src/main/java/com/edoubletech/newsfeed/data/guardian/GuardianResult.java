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

package com.edoubletech.newsfeed.data.guardian;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuardianResult implements Parcelable {
    public static final Creator<GuardianResult> CREATOR = new Creator<GuardianResult>() {
        @Override
        public GuardianResult createFromParcel(Parcel in) {
            return new GuardianResult(in);
        }
        
        @Override
        public GuardianResult[] newArray(int size) {
            return new GuardianResult[size];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("webTitle")
    @Expose
    private String webTitle;
    @SerializedName("webPublicationDate")
    @Expose
    private String webPublicationDate;
    @SerializedName("fields")
    @Expose
    private GuardianFields fields;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("apiUrl")
    @Expose
    private String apiUrl;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    
    private GuardianResult(Parcel in) {
        type = in.readString();
        sectionId = in.readString();
        webTitle = in.readString();
        webPublicationDate = in.readString();
        id = in.readString();
        fields = in.readParcelable(GuardianFields.class.getClassLoader());
        webUrl = in.readString();
        apiUrl = in.readString();
        sectionName = in.readString();
    }
    
    public String getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    public String getSectionId() {
        return sectionId;
    }
    
    public String getWebTitle() {
        return webTitle;
    }
    
    public String getWebPublicationDate() {
        return webPublicationDate;
    }
    
    public GuardianFields getFields() {
        return fields;
    }
    
    public String getWebUrl() {
        return webUrl;
    }
    
    public String getSectionName() {
        return sectionName;
    }
    
    public String getApiUrl() {
        return apiUrl;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(sectionId);
        dest.writeString(webTitle);
        dest.writeString(webPublicationDate);
        dest.writeString(id);
        dest.writeParcelable(fields, flags);
        dest.writeString(webUrl);
        dest.writeString(apiUrl);
        dest.writeString(sectionName);
    }
}
