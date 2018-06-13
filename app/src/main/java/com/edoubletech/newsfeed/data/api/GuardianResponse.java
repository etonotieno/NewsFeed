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

package com.edoubletech.newsfeed.data.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GuardianResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userTier")
    @Expose
    private String userTier;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("orderBy")
    @Expose
    private String orderBy;
    @SerializedName("results")
    @Expose
    private List<GuardianResult> results = new ArrayList<>();

    protected GuardianResponse(Parcel in) {
        status = in.readString();
        userTier = in.readString();
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readInt();
        }
        if (in.readByte() == 0) {
            startIndex = null;
        } else {
            startIndex = in.readInt();
        }
        if (in.readByte() == 0) {
            pageSize = null;
        } else {
            pageSize = in.readInt();
        }
        if (in.readByte() == 0) {
            currentPage = null;
        } else {
            currentPage = in.readInt();
        }
        if (in.readByte() == 0) {
            pages = null;
        } else {
            pages = in.readInt();
        }
        orderBy = in.readString();
        results = in.createTypedArrayList(GuardianResult.CREATOR);
    }

    public static final Creator<GuardianResponse> CREATOR = new Creator<GuardianResponse>() {
        @Override
        public GuardianResponse createFromParcel(Parcel in) {
            return new GuardianResponse(in);
        }

        @Override
        public GuardianResponse[] newArray(int size) {
            return new GuardianResponse[size];
        }
    };

    public List<GuardianResult> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public String getUserTier() {
        return userTier;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPages() {
        return pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(userTier);
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total);
        }
        if (startIndex == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(startIndex);
        }
        if (pageSize == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(pageSize);
        }
        if (currentPage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currentPage);
        }
        if (pages == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(pages);
        }
        parcel.writeString(orderBy);
        parcel.writeTypedList(results);
    }
}
