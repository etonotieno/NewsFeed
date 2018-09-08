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

package com.edoubletech.newsfeed.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.News;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import static com.edoubletech.newsfeed.ui.fragments.MainFragment.EXTRA_KEY;
import static com.edoubletech.newsfeed.utils.DateUtils.getPrettifiedTimeString;
import static com.edoubletech.newsfeed.utils.DateUtils.getTimeDifferenceInSeconds;

public class DetailActivity extends AppCompatActivity {
    
    private TextView mTitleTextView, mTimePassedTextView, mPublicationDateTextView, mDescriptionTextView;
    private Button mWebButton;
    private ImageView mArticleImageView;
    private News clickedNewsItem;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        
        mTimePassedTextView = findViewById(R.id.time_text_view);
        mPublicationDateTextView = findViewById(R.id.publication_date_textview);
        mDescriptionTextView = findViewById(R.id.description_detail);
        mTitleTextView = findViewById(R.id.title_detail);
        mWebButton = findViewById(R.id.web_button);
        mArticleImageView = findViewById(R.id.detail_image_view);
        
        Intent parentIntent = getIntent();
        Bundle bundle = parentIntent.getExtras();
        clickedNewsItem = bundle.getParcelable(EXTRA_KEY);
        
        collapsingToolbarLayout.setTitle(clickedNewsItem.getSectionName());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        bindViews();
    }
    
    public void bindViews() {
        String dateString = clickedNewsItem.getPublicationDate();
        long secondsPassedBetweenDates = getTimeDifferenceInSeconds(dateString);
        String correctTimeString = getPrettifiedTimeString(secondsPassedBetweenDates);
        mTimePassedTextView.setText(correctTimeString);
        
        String date = clickedNewsItem.getPublicationDate();
        DateTime dateTime = new DateTime(date);
        String dateVar = dateTime.toLocalDate().toString();
        mPublicationDateTextView.setText(dateVar);
        
        mDescriptionTextView.setText(Html.fromHtml(clickedNewsItem.getBodyText()));
        mDescriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
        
        mTitleTextView.setText(clickedNewsItem.getTitle());

        Picasso.get().load(clickedNewsItem.getImageUrl()).into(mArticleImageView);

        mWebButton.setOnClickListener(v -> {
            String url = clickedNewsItem.getWebUrl();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(getResources().getColor(R.color.primary));
            builder.enableUrlBarHiding();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(DetailActivity.this, Uri.parse(url));
        });
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
    
}
