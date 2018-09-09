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

package com.edoubletech.newsfeed.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.edoubletech.newsfeed.BuildConfig;
import com.edoubletech.newsfeed.data.guardian.GuardianMain;
import com.edoubletech.newsfeed.data.model.News;
import com.edoubletech.newsfeed.data.networking.Injector;
import com.edoubletech.newsfeed.data.networking.Service;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class Repository {

    private MutableLiveData<List<News>> mNewsList = new MutableLiveData<>();
    private GuardianMapper articleMapper = new GuardianMapper();
    private static Repository sInstance;

    public static Repository getInstance(String categoryName) {
        if (sInstance == null) {
            synchronized (Repository.class) {
                if (sInstance == null) {
                    sInstance = new Repository(categoryName);
                }
            }
        }
        return sInstance;
    }

    private Repository(String categoryName) {
        Service service = Injector.provideRetrofit().create(Service.class);
        Call<GuardianMain> call = service.getNews("50", BuildConfig.GUARDIAN_API_KEY,
                categoryName, "all", "json");

        // Make the actual call. This is an asynchronous call.
        call.enqueue(new Callback<GuardianMain>() {
            @Override
            public void onResponse(Call<GuardianMain> call, Response<GuardianMain> response) {
                if (response.isSuccessful()) {
                    Timber.d(" NewsResponse is successful");
                    mNewsList.postValue(articleMapper.mapToModel(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    Timber.i("Network Error: " + errorBody.toString()
                            + "\nStatus Code: " + statusCode);

                }
            }

            @Override
            public void onFailure(Call<GuardianMain> call1, Throwable throwable) {
                Timber.e(throwable);
            }
        });
    }

    public LiveData<List<News>> search() {
        return mNewsList;
    }
}
