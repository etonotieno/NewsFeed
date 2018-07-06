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

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.Category;
import com.edoubletech.newsfeed.ui.activities.CategoryActivity;
import com.edoubletech.newsfeed.ui.adapters.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EtonOtieno on 3/7/2018
 */

public class CategoryFragment extends Fragment implements CategoryAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private List<Category> mCategories = new ArrayList<Category>() {{
        add(new Category(getString(R.string.arts_section), R.drawable.ic_arts));
        add(new Category(getString(R.string.books_section), R.drawable.ic_books));
        add(new Category(getString(R.string.business_section), R.drawable.ic_business));
        add(new Category(getString(R.string.education_section), R.drawable.ic_education));
        add(new Category(getString(R.string.fashion_section), R.drawable.ic_fashion));
        add(new Category(getString(R.string.film_section), R.drawable.ic_film));
        add(new Category(getString(R.string.health_section), R.drawable.ic_health));
        add(new Category(getString(R.string.lifestyle_section), R.drawable.ic_lifestyle));
        add(new Category(getString(R.string.media_section), R.drawable.ic_media));
        add(new Category(getString(R.string.money_section), R.drawable.ic_money));
        add(new Category(getString(R.string.music_section), R.drawable.ic_music));
        add(new Category(getString(R.string.science_section), R.drawable.ic_science));
        add(new Category(getString(R.string.sports_section), R.drawable.ic_sports));
        add(new Category(getString(R.string.technology_section), R.drawable.ic_technology));
        add(new Category(getString(R.string.travel_section), R.drawable.ic_travel));
        add(new Category(getString(R.string.world_section), R.drawable.ic_world_news));
    }};

    public final static String EXTRA_CATEGORY_NAME = "com.edoubletech.newsfeed.EXTRA_CATEGORY_NAME";

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        mRecyclerView = rootView.findViewById(R.id.category_recycler_view);
        setUpRecyclerView();

        return rootView;
    }

    public void setUpRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.setSpanCount(4);
        } else {
            layoutManager.setSpanCount(3);
        }

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new CategoryAdapter(mCategories, this);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Category category = mCategories.get(clickedItemIndex);
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, category.getName());
        startActivity(intent);
    }
}
