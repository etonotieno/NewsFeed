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

package com.edoubletech.newsfeed.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.edoubletech.newsfeed.BuildConfig;
import com.edoubletech.newsfeed.data.api.GuardianMain;
import com.edoubletech.newsfeed.data.api.GuardianResponse;
import com.edoubletech.newsfeed.data.api.GuardianResult;
import com.edoubletech.newsfeed.data.model.News;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class Repository {

    private String BASE_URL = "https://content.guardianapis.com/";

    private MutableLiveData<List<News>> newsLiveData = new MutableLiveData<>();

    private HttpLoggingInterceptor interceptor =
            new HttpLoggingInterceptor(message -> Timber.d(message));

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Repository(String categoryName) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        List<News> news = new ArrayList<>();
        NewsService newsService = retrofit.create(NewsService.class);
        Call<GuardianMain> call = newsService.getNews("50", BuildConfig.GUARDIAN_API_KEY,
                categoryName, "all", "json");

        // Make the actual call. This is an asynchronous call.
        call.enqueue(new Callback<GuardianMain>() {
            @Override
            public void onResponse(Call<GuardianMain> call, Response<GuardianMain> response) {
                if (response.isSuccessful()) {
                    Timber.d(" NewsResponse is successful");
                    GuardianResponse res = response.body().getResponse();
                    List<GuardianResult> apiResults = res.getResults();
                    for (GuardianResult apiResult : apiResults) {
                        news.add(new News(
                                apiResult.getFields().getThumbnail(), /* Thumbnail for the news */
                                apiResult.getWebUrl(), /* Website url*/
                                apiResult.getSectionName(), /* Section name*/
                                apiResult.getWebTitle(), /* Web Title of Article*/
                                apiResult.getFields().getTrailText(), /* Trail Text*/
                                apiResult.getFields().getBodyText(), /* Description */
                                apiResult.getWebPublicationDate())); /* Publication Date*/
                    }
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
        newsLiveData.setValue(news);
    }

    public LiveData<List<News>> getNewsList() {
        return newsLiveData;
    }
}