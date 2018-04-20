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

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edoubletech.newsfeed.MainViewModel;
import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.adapter.NewsAdapter;
import com.edoubletech.newsfeed.fragments.CategoryFragment;
import com.edoubletech.newsfeed.model.News;

import java.util.ArrayList;
import java.util.List;

import static com.edoubletech.newsfeed.fragments.MainFragment.EXTRA_KEY;

public class CategoryActivity extends AppCompatActivity implements
        NewsAdapter.ListItemClickListener {
    
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
        
        mEmptyStateTextView = findViewById(R.id.main_fragment_empty_view);
        mRecyclerView = findViewById(R.id.category_activity_recycler_view);
        mLoadingIndicator = findViewById(R.id.category_loading_indicator);
        mNoInternetImage = findViewById(R.id.no_internet_image_main_fragment);
        categoryName = getIntent().getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);
        mLoadingIndicator.setVisibility(View.GONE);
        mNoInternetImage.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        
        getSupportActionBar().setTitle(categoryName);
        
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        
        mNewsAdapter = new NewsAdapter(this, this);
        mRecyclerView.setAdapter(mNewsAdapter);
        
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.search(getSectionId(categoryName));
     
        viewModel.mNewsList.observe(this, news -> {
            mArticles = new ArrayList<>(news);
            mNewsAdapter.setNews(news);
        });
    }
    
    public String getSectionId(String categoryName) {
        String formattedSectionName = categoryName.replaceAll("\\s", "")
                .toLowerCase();
        
        String[] categoryNames = {"health", "sports", "worldnews"};
        String[] correctSectionIds = {"healthcare-network", "sport", "world"};
        
        for (int index = 0; index < categoryNames.length; index++) {
            if (formattedSectionName.toLowerCase().contains(categoryNames[index])) {
                return correctSectionIds[index];
            }
        }
        
        return formattedSectionName;
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
