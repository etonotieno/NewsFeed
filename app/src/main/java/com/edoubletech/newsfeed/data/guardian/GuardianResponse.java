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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GuardianResponse {
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
}
