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

package com.edoubletech.newsfeed.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.News;
import com.edoubletech.newsfeed.databinding.FragmentMainBinding;
import com.edoubletech.newsfeed.view.DetailActivity;
import com.edoubletech.newsfeed.view.adapters.NewsAdapter;
import com.edoubletech.newsfeed.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.edoubletech.newsfeed.view.category.CategoryActivity.NEWS_ID_KEY;

public class MainFragment extends Fragment implements NewsAdapter.ListItemClickListener {

    public final static String EXTRA_KEY = "com.edoubletech.newsfeed.EXTRA_KEY";
    private NewsAdapter mNewsAdapter;
    private List<News> mArticles;

    public MainFragment() {
    }

    FragmentMainBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        binding.categoryLoadingIndicator.setVisibility(View.GONE);
        binding.noInternetImageFragment.setVisibility(View.GONE);
        binding.mainFragmentEmptyView.setVisibility(View.GONE);
        binding.categoryActivityRecyclerView.setVisibility(View.VISIBLE);

        binding.categoryActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        binding.categoryActivityRecyclerView.setHasFixedSize(true);

        mNewsAdapter = new NewsAdapter(getActivity(), this);
        binding.categoryActivityRecyclerView.setAdapter(mNewsAdapter);
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.search("technology");

        viewModel.getNewsList().observe(this, news -> {
            mArticles = new ArrayList<>(news);
            mNewsAdapter.setNews(news);
        });

        return binding.getRoot();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        News clickedArticle = mArticles.get(clickedItemIndex);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(NEWS_ID_KEY, clickedArticle.getId());
        startActivity(intent);
    }
}
