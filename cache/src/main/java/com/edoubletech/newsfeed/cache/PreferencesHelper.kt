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

package com.edoubletech.newsfeed.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
open class PreferencesHelper(context: Context) {

    companion object {
        private const val PREF_FILE_NAME = "com.edoubletech.newsfeed.cache.file"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = pref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = pref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}
