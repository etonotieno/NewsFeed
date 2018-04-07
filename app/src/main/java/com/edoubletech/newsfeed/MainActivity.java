/*
 * Copyright (C) 2018 Eton Otieno Oboch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.edoubletech.newsfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.edoubletech.newsfeed.adapter.NewsAdapter;
import com.edoubletech.newsfeed.model.Article;
import com.edoubletech.newsfeed.model.NewsResponse;
import com.edoubletech.newsfeed.networking.CacheInterceptor;
import com.edoubletech.newsfeed.networking.Client;
import com.edoubletech.newsfeed.networking.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

/**
 * @author EtonOtieno
 */

public class MainActivity extends AppCompatActivity {
    
   
    private NewsAdapter mNewsAdapter;
    private RecyclerView mRecyclerView;
    private List<Article> mArticles = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        
        if (getActivity().getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,
                    2, LinearLayoutManager.VERTICAL, false));
        }
        
        mRecyclerView.setHasFixedSize(true);
        mNewsAdapter = new NewsAdapter(MainActivity.this, mArticles);
        mRecyclerView.setAdapter(mNewsAdapter);
        
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Level.BODY);
        
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        
        // Add the interceptor to the client
        httpClient.networkInterceptors().add(new CacheInterceptor(this));
        
        // Set up the cache
        File cacheDirectory = new File(this.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheDirectory, cacheSize);
        httpClient.cache(cache);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        
        // add logging as last interceptor
        httpClient.addInterceptor(loggingInterceptor);
        
        Service service = Client.getClient(httpClient).create(Service.class);
        Call<NewsResponse> call = service.getNews("techcrunch", "en");
        
        // Make the actual call. This is an asynchronous call.
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    mArticles.clear();
                    mArticles.addAll(response.body().getArticles());
                    mNewsAdapter.notifyDataSetChanged();
                }
            }
            
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e(MainActivity.class.getSimpleName(), " " + t.getMessage(), t);
            }
        });
    }
    
    public Activity getActivity() {
        Context context = this;
        return (Activity) context;
    }
  
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        
        return super.onOptionsItemSelected(item);
    }
    
}
