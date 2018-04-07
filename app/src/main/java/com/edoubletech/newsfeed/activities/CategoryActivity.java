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

package com.edoubletech.newsfeed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edoubletech.newsfeed.BuildConfig;
import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.adapter.NewsAdapter;
import com.edoubletech.newsfeed.fragments.CategoryFragment;
import com.edoubletech.newsfeed.guardian.GuardianMain;
import com.edoubletech.newsfeed.guardian.GuardianResponse;
import com.edoubletech.newsfeed.guardian.GuardianResult;
import com.edoubletech.newsfeed.model.News;
import com.edoubletech.newsfeed.networking.Injector;
import com.edoubletech.newsfeed.networking.Service;
import com.edoubletech.newsfeed.utilities.NewsFeed;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.edoubletech.newsfeed.fragments.MainFragment.EXTRA_KEY;

public class CategoryActivity extends AppCompatActivity implements
        NewsAdapter.ListItemClickListener {
    
    public boolean isFromCache;
    public boolean isFromServer;
    private NewsAdapter mNewsAdapter;
    private RecyclerView mRecyclerView;
    private List<News> mArticles;
    private TextView mEmptyStateTextView;
    private ImageView mNoInternetImage;
    private View mLoadingIndicator;
    private String categoryName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        
        mArticles = new ArrayList<>();
        mEmptyStateTextView = findViewById(R.id.main_fragment_empty_view);
        mRecyclerView = findViewById(R.id.category_activity_recycler_view);
        mLoadingIndicator = findViewById(R.id.category_loading_indicator);
        mNoInternetImage = findViewById(R.id.no_internet_image_main_fragment);
        categoryName = getIntent().getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);
        
        getSupportActionBar().setTitle(categoryName);
        
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        
        mNewsAdapter = new NewsAdapter(this, mArticles, this);
        mRecyclerView.setAdapter(mNewsAdapter);
        
        if (NewsFeed.Companion.hasNetwork()) {
            mEmptyStateTextView.setVisibility(View.GONE);
            mNoInternetImage.setVisibility(View.GONE);
        }
        
        Service service = Injector.provideRetrofit().create(Service.class);
        
        Call<GuardianMain> call = service.getNews("50", BuildConfig.GUARDIAN_API_KEY,
                categoryName.toLowerCase(), "all", "json");
        
        // Make the actual call. This is an asynchronous call.
        call.enqueue(new Callback<GuardianMain>() {
            @Override
            public void onResponse(Call<GuardianMain> call, Response<GuardianMain> response) {
                if (response.raw().cacheResponse() != null) {
                    //Response is from cache
                    isFromCache = true;
                }
                if (response.raw().networkResponse() != null) {
                    //Response is from the server
                    isFromServer = true;
                }
                
                if (response.isSuccessful()) {
                    Timber.d(" NewsResponse is successful");
                    GuardianResponse res = response.body().getResponse();
                    List<GuardianResult> apiResults = res.getResults();
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
                    mEmptyStateTextView.setVisibility(View.GONE);
                    mNoInternetImage.setVisibility(View.GONE);
                    mLoadingIndicator.setVisibility(View.GONE);
                    mNewsAdapter.notifyDataSetChanged();
                    if (mArticles.isEmpty()) {
                        mLoadingIndicator.setVisibility(View.GONE);
                        mNoInternetImage.setVisibility(View.GONE);
                        mEmptyStateTextView.setVisibility(View.VISIBLE);
                        mEmptyStateTextView.setText(getString(R.string.empty_results_error));
                    }
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    Timber.i("Network Error: " + errorBody.toString()
                            + "\nStatus Code: " + statusCode);
                    mLoadingIndicator.setVisibility(View.GONE);
                    mNoInternetImage.setVisibility(View.GONE);
                    mEmptyStateTextView.setVisibility(View.VISIBLE);
                    mEmptyStateTextView.setText(getString(R.string.error_loading_results));
                }
            }
            
            @Override
            public void onFailure(Call<GuardianMain> call1, Throwable throwable) {
                Timber.e(throwable);
            }
        });
        
        if (!NewsFeed.Companion.hasNetwork() && !isFromCache && !isFromServer) {
            mLoadingIndicator.setVisibility(View.GONE);
            mNoInternetImage.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(getString(R.string.no_internet_error));
        }
        
    }
    
    @Override
    public void onListItemClick(int clickedItemIndex) {
        News clickedArticle = mArticles.get(clickedItemIndex);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_KEY, clickedArticle);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
