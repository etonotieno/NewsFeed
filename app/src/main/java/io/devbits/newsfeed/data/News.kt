/*
 *  Copyright (C) 2019 Eton Otieno
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
 */

package io.devbits.newsfeed.data

/**
 * Created by EtonOtieno on 3/21/2018
 */

data class News(
    val id: String,
    val imageUrl: String? = "",
    val webUrl: String = "",
    val sectionName: String = "",
    val title: String = "",
    val trailText: String = "",
    val bodyText: String = "",
    val publicationDate: String = ""
)
