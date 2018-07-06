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

package com.edoubletech.newsfeed.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.News;
import com.edoubletech.newsfeed.ui.MainViewModel;
import com.edoubletech.newsfeed.ui.activities.DetailActivity;
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements NewsAdapter.ListItemClickListener {
    
    public final static String EXTRA_KEY = "com.edoubletech.newsfeed.EXTRA_KEY";
    private NewsAdapter mNewsAdapter;
    private RecyclerView mRecyclerView;
    private List<News> mArticles;
    
    public MainFragment() {
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        
        mRecyclerView = rootView.findViewById(R.id.category_activity_recycler_view);

        mRecyclerView.setVisibility(View.VISIBLE);
        
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        
        mNewsAdapter = new NewsAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mNewsAdapter);
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.search("technology");
    
        viewModel.getNewsList().observe(this, news -> {
            mArticles = new ArrayList<>(news);
            mNewsAdapter.setNews(news);
        });
    
        return rootView;
    }
    
    @Override
    public void onListItemClick(int clickedItemIndex) {
        News clickedArticle = mArticles.get(clickedItemIndex);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_KEY, clickedArticle);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
