/*
 *  Copyright 2019 Eton Otieno
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
 */

package io.devbits.newsfeed.newsdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.devbits.newsfeed.R
import io.devbits.newsfeed.data.Origin
import kotlinx.android.synthetic.main.activity_detail.detail_image_view
import kotlinx.android.synthetic.main.activity_detail.newsDetailBodyTextView
import kotlinx.android.synthetic.main.activity_detail.newsDetailTitleTextView
import kotlinx.android.synthetic.main.activity_detail.toolbar

class DetailActivity : AppCompatActivity() {

    private val args by navArgs<DetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val newsItem = args.newsItem
        Glide.with(this)
            .load(newsItem.imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(detail_image_view)

        val body = when (newsItem.origin) {
            Origin.GUARDIAN_API -> newsItem.body?.let {
                HtmlCompat.fromHtml(
                    it,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                ).toString()
            }
            Origin.NEWS_API -> newsItem.body
        }
        newsDetailBodyTextView.text = body
        newsDetailTitleTextView.text = newsItem.title
    }

}
