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

public class GuardianMain implements Parcelable {
    
    public static final Creator<GuardianMain> CREATOR = new Creator<GuardianMain>() {
        @Override
        public GuardianMain createFromParcel(Parcel in) {
            return new GuardianMain(in);
        }
        
        @Override
        public GuardianMain[] newArray(int size) {
            return new GuardianMain[size];
        }
    };
    @SerializedName("response")
    @Expose
    private GuardianResponse response;
    
    private GuardianMain(Parcel in) {
        response = in.readParcelable(GuardianResponse.class.getClassLoader());
    }
    
    public GuardianResponse getResponse() {
        return response;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeParcelable(response, flags);
    }
}