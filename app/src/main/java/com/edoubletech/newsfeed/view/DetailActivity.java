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

package com.edoubletech.newsfeed.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.edoubletech.newsfeed.R;
import com.edoubletech.newsfeed.data.model.News;
import com.edoubletech.newsfeed.databinding.ActivityDetailBinding;

import org.joda.time.DateTime;

import static com.edoubletech.newsfeed.utils.NewsDateUtilsKt.getPrettifiedTimeString;
import static com.edoubletech.newsfeed.utils.NewsDateUtilsKt.getTimeDifferenceInSeconds;
import static com.edoubletech.newsfeed.view.main.MainFragment.EXTRA_KEY;

public class DetailActivity extends AppCompatActivity {

    private News clickedNewsItem;
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        Intent parentIntent = getIntent();
        Bundle bundle = parentIntent.getExtras();
        clickedNewsItem = bundle.getParcelable(EXTRA_KEY);

        binding.collapsingToolbar.setTitle(clickedNewsItem.getSectionName());
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        bindViews();
    }

    public void bindViews() {
        String dateString = clickedNewsItem.getPublicationDate();
        long secondsPassedBetweenDates = getTimeDifferenceInSeconds(dateString);
        String correctTimeString = getPrettifiedTimeString(secondsPassedBetweenDates);
        binding.setDateString(correctTimeString);

        String date = clickedNewsItem.getPublicationDate();
        DateTime dateTime = new DateTime(date);
        String dateVar = dateTime.toLocalDate().toString();
        binding.publicationDateTextview.setText(dateVar);

        binding.descriptionDetail.setText(Html.fromHtml(clickedNewsItem.getBodyText()));
        binding.descriptionDetail.setMovementMethod(LinkMovementMethod.getInstance());

        binding.titleDetail.setText(clickedNewsItem.getTitle());

        Glide.with(this).load(clickedNewsItem.getImageUrl()).into(binding.detailImageView);

        binding.webButton.setOnClickListener(v -> {
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
