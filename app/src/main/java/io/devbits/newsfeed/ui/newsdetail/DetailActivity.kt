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

package io.devbits.newsfeed.ui.newsdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.devbits.newsfeed.data.model.Origin
import io.devbits.newsfeed.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val args by navArgs<DetailActivityArgs>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val newsItem = args.newsItem
        Glide.with(this)
            .load(newsItem.imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.detailImageView)

        val body = when (newsItem.origin) {
            Origin.GUARDIAN_API -> newsItem.body?.let {
                HtmlCompat.fromHtml(
                    it,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                ).toString()
            }

            Origin.NEWS_API -> newsItem.body
        }

        binding.newsDetailBodyTextView.text = body
        binding.newsDetailTitleTextView.text = newsItem.title
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
