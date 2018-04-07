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

package com.edoubletech.newsfeed.guardian;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuardianFields implements Parcelable {
    public static final Creator<GuardianFields> CREATOR = new Creator<GuardianFields>() {
        @Override
        public GuardianFields createFromParcel(Parcel in) {
            return new GuardianFields(in);
        }
        
        @Override
        public GuardianFields[] newArray(int size) {
            return new GuardianFields[size];
        }
    };
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("body")
    @Expose
    private String bodyText;
    @SerializedName("trailText")
    @Expose
    private String trailText;
    
    private GuardianFields(Parcel in) {
        thumbnail = in.readString();
        bodyText = in.readString();
        trailText = in.readString();
    }
    
    public String getThumbnail() {
        return thumbnail;
    }
    
    public String getBodyText() {
        return bodyText;
    }
    
    public String getTrailText() {
        return trailText;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeString(thumbnail);
        dest.writeString(bodyText);
        dest.writeString(trailText);
    }
}
