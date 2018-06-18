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

package com.edoubletech.newsfeed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.model.News;
import com.edoubletech.newsfeed.utilities.DateUtilsKt;

import java.util.List;

/**
 * Created by EtonOtieno on 2/15/2018
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    
    final private ListItemClickListener mOnClickListener;
    private Context mContext;
    private List<News> mNewsList;
    
    public NewsAdapter(Context context, ListItemClickListener listener) {
        this.mContext = context;
        this.mOnClickListener = listener;
    }
    
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News currentNews = mNewsList.get(position);
        holder.mHeadlineTextView.setText(currentNews.getTitle());
        
        holder.mTrailTextTextView.setText(currentNews.getTrailText());
        
        holder.mSectionTextView.setText(currentNews.getSectionName());
        
        String dateString = currentNews.getPublicationDate();
        long secondsPassedBetweenDates = DateUtilsKt.getTimeDifferenceInSeconds(dateString);
        String correctTimeString = DateUtilsKt.getPrettifiedTimeString(secondsPassedBetweenDates);
        
        holder.mPublicationTime.setText(correctTimeString);
        
        String imageUrl = currentNews.getImageUrl();
        if (imageUrl == null) {
            holder.mArticleImageView.setVisibility(View.GONE);
        } else {
            Glide.with(mContext)
                    .load(imageUrl)
                    .into(holder.mArticleImageView);
        }
        holder.itemView.setTag(currentNews);
    }
    
    @Override
    public int getItemCount() {
        return (mNewsList != null) ? mNewsList.size() : 0;
    }
    
    public void setNews(List<News> newListOfNews) {
        mNewsList = newListOfNews;
        notifyDataSetChanged();
        
    }
    
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    
    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTrailTextTextView, mHeadlineTextView, mSectionTextView, mPublicationTime;
        ImageView mArticleImageView;
        
        NewsViewHolder(View itemView) {
            super(itemView);
            mHeadlineTextView = itemView.findViewById(R.id.headline_text_view);
            mTrailTextTextView = itemView.findViewById(R.id.trailtext_text_view);
            mArticleImageView = itemView.findViewById(R.id.article_image_view);
            mSectionTextView = itemView.findViewById(R.id.section_text_view);
            mPublicationTime = itemView.findViewById(R.id.time_text_view);
            itemView.setOnClickListener(this);
        }
        
        
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
