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

public class GuardianResult {

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
}
