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

package com.edoubletech.newsfeed.view.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.News;
import com.edoubletech.newsfeed.databinding.NewsItemBinding;
import com.edoubletech.newsfeed.utils.NewsDateUtilsKt;

/**
 * Created by EtonOtieno on 2/15/2018
 */

public class NewsAdapter extends PagedListAdapter<News, NewsAdapter.NewsViewHolder> {

    private Context mContext;

    public NewsAdapter(Context context) {
        super(new DiffUtil.ItemCallback<News>() {
            @Override
            public boolean areItemsTheSame(News oldItem, News newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(News oldItem, News newItem) {
                return oldItem == newItem;
            }
        });
        mContext = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.news_item, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News currentNews = getItem(position);
        if (currentNews != null) {
            holder.binding.setNewsItem(currentNews);

            String dateString = currentNews.getPublicationDate();
            long secondsPassedBetweenDates = NewsDateUtilsKt.getTimeDifferenceInSeconds(dateString);
            String correctTimeString = NewsDateUtilsKt.getPrettifiedTimeString(secondsPassedBetweenDates);

            holder.binding.setTimeString(correctTimeString);

            String imageUrl = currentNews.getImageUrl();
            if (imageUrl == null || imageUrl.isEmpty()) {
                holder.binding.articleImageView.setVisibility(View.GONE);
            } else {
                Glide.with(mContext)
                        .load(imageUrl)
                        .into(holder.binding.articleImageView);
            }
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        private NewsItemBinding binding;

        NewsViewHolder(NewsItemBinding newsItemBinding) {
            super(newsItemBinding.getRoot());
            binding = newsItemBinding;
        }
    }
}
