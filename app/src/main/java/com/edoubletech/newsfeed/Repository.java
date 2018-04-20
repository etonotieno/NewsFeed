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

package com.edoubletech.newsfeed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.edoubletech.newsfeed.guardian.GuardianMain;
import com.edoubletech.newsfeed.guardian.GuardianResponse;
import com.edoubletech.newsfeed.guardian.GuardianResult;
import com.edoubletech.newsfeed.model.News;
import com.edoubletech.newsfeed.networking.Injector;
import com.edoubletech.newsfeed.networking.Service;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class Repository {
    
    private static Repository sInstance;
    
    public static Repository getInstance() {
        if (sInstance == null) {
            synchronized (Repository.class) {
                if (sInstance == null) {
                    sInstance = new Repository();
                }
            }
        }
        return sInstance;
    }
    
    public LiveData<List<News>> search(String categoryName) {
        MutableLiveData<List<News>> mNewsList = new MutableLiveData<>();
        Service service = Injector.provideRetrofit().create(Service.class);
        Call<GuardianMain> call = service.getNews("50", BuildConfig.GUARDIAN_API_KEY,
                categoryName, "all", "json");
        
        // Make the actual call. This is an asynchronous call.
        call.enqueue(new Callback<GuardianMain>() {
            @Override
            public void onResponse(Call<GuardianMain> call, Response<GuardianMain> response) {
                if (response.isSuccessful()) {
                    Timber.d(" NewsResponse is successful");
                    GuardianResponse res = response.body().getResponse();
                    List<GuardianResult> apiResults = res.getResults();
                    List<News> mArticles = new ArrayList<>();
                    for (GuardianResult apiResult : apiResults) {
                        mArticles.add(new News(
                                apiResult.getFields().getThumbnail(), /* Thumbnail for the news */
                                apiResult.getWebUrl(), /* Website url*/
                                apiResult.getSectionName(), /* Section name*/
                                apiResult.getWebTitle(), /* Web Title of Article*/
                                apiResult.getFields().getTrailText(), /* Trail Text*/
                                apiResult.getFields().getBodyText(), /* Description */
                                apiResult.getWebPublicationDate())); /* Publication Date*/
                    }
                    mNewsList.postValue(mArticles);
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
        return mNewsList;
    }
}
