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

package com.edoubletech.newsfeed.view.category;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.News;
import com.edoubletech.newsfeed.databinding.ActivityCategoryBinding;
import com.edoubletech.newsfeed.view.DetailActivity;
import com.edoubletech.newsfeed.view.adapters.NewsAdapter;
import com.edoubletech.newsfeed.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements
        NewsAdapter.ListItemClickListener {

    private NewsAdapter mNewsAdapter;
    private List<News> mArticles;
    private String categoryName;

    private ActivityCategoryBinding binding;
    public static final String NEWS_ID_KEY = "com.edoubletech.newsfeed.NEWS_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);

        categoryName = getIntent().getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);
        binding.categoryLoadingIndicator.setVisibility(View.GONE);
        binding.noInternetImage.setVisibility(View.GONE);
        binding.mainFragmentEmptyView.setVisibility(View.GONE);
        binding.categoryActivityRecyclerView.setVisibility(View.VISIBLE);

        getSupportActionBar().setTitle(categoryName);

        binding.categoryActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        binding.categoryActivityRecyclerView.setHasFixedSize(true);

        mNewsAdapter = new NewsAdapter(this, this);
        binding.categoryActivityRecyclerView.setAdapter(mNewsAdapter);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.search(getSectionId(categoryName));

        viewModel.getNewsList().observe(this, news -> {
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
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(NEWS_ID_KEY, clickedArticle.getId());
        startActivity(intent);
    }
}
