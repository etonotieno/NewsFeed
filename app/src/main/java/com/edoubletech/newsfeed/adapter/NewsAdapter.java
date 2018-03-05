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

package com.edoubletech.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.model.Article;

import java.util.List;

/**
 * Created by EtonOtieno on 2/15/2018
 * <p>
 * This is a custom adapter that extends the {@link ArrayAdapter} class.
 * An Adapter object acts as a bridge between an {@link AdapterView} and the
 * underlying data for that view. The Adapter provides access to the data items.
 * The Adapter is also responsible for making a {@link View} for
 * each item in the data set.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    
    private Context mContext;
    private List<Article> mNewsList;
    
    public NewsAdapter(Context context, List<Article> newsResponses) {
        this.mContext = context;
        this.mNewsList = newsResponses;
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
        holder.mAuthorTextView.setText(mNewsList.get(position).getAuthor());
        holder.mTitleTextView.setText(mNewsList.get(position).getTitle());
        holder.mDescriptionTextView.setText(mNewsList.get(position).getDescription());
        holder.mNewsSource.setText(mNewsList.get(position).getSource().getName());
        
        
        String imageUrl = mNewsList.get(position).getUrlToImage();
        
        Glide.with(mContext)
                .load(imageUrl)
                .into(holder.mArticleImageView);
    }
    
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
    
    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView mTitleTextView, mDescriptionTextView, mAuthorTextView, mNewsSource;
        ImageView mArticleImageView;
        
        NewsViewHolder(View itemView) {
            super(itemView);
            
            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mDescriptionTextView = itemView.findViewById(R.id.description_text_view);
            mAuthorTextView = itemView.findViewById(R.id.author_text_view);
            mNewsSource = itemView.findViewById(R.id.news_source_text_view);
            mNewsSource.setVisibility(View.VISIBLE);
            mArticleImageView = itemView.findViewById(R.id.article_image_view);
    
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                
                if (position != RecyclerView.NO_POSITION){
                    Article article = mNewsList.get(position);
                    Uri newsUri = Uri.parse(article.getUrl());
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                    mContext.startActivity(webIntent);
                }
            });
        }
        
    }
}
