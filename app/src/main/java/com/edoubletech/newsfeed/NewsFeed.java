/*
 *   Copyright (C) 2018 Eton Otieno Oboch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.edoubletech.newsfeed;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.edoubletech.newsfeed.di.DaggerNewsComponent;
import com.edoubletech.newsfeed.di.DatabaseModule;
import com.edoubletech.newsfeed.di.NetworkingModule;
import com.edoubletech.newsfeed.di.NewsComponent;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

public class NewsFeed extends Application {

    private static NewsComponent newsComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        newsComponent = DaggerNewsComponent
                .builder()
                .databaseModule(new DatabaseModule(this))
                .networkingModule(new NetworkingModule())
                .build();


        JodaTimeAndroid.init(this);

    }

    public static NewsComponent getNewsComponent() {
        return newsComponent;
    }

    public boolean hasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = (cm != null) ? cm.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected();
    }
}
