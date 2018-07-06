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

package com.edoubletech.newsfeed.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.edoubletech.newsfeed.BuildConfig
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

/**
 * Created by EtonOtieno on 3/13/2018
 */

class NewsFeed : Application() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)

        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {

        fun hasNetwork(): Boolean {
            return instance!!.checkIfHasNetwork()
        }

        var instance: NewsFeed? = null
    }
}
