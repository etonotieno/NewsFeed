/*
 *  Copyright (C) 2019 Eton Otieno
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

package io.devbits.newsfeed.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.devbits.newsfeed.data.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsAdapter : ListAdapter<DataItem, RecyclerView.ViewHolder>(COMPARATOR) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<News>) {
        adapterScope.launch {
            val items = mutableListOf<DataItem>()
            items.add(DataItem.HeadlineHeader)
            items.addAll(list.map { DataItem.HeadlineItem(it) }.take(4))
            items.add(DataItem.TopStoryHeader)
            items.addAll(list.map { DataItem.TopStoryItem(it) })
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            DataItem.TopStoryHeader -> ITEM_VIEW_TYPE_TOP_STORY_HEADER
            is DataItem.TopStoryItem -> ITEM_VIEW_TYPE_TOP_STORY_ITEM
            DataItem.HeadlineHeader -> ITEM_VIEW_TYPE_HEADLINE_HEADER
            is DataItem.HeadlineItem -> ITEM_VIEW_TYPE_HEADLINE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_TOP_STORY_HEADER -> TopStoriesHeaderViewHolder.create(parent)
            ITEM_VIEW_TYPE_TOP_STORY_ITEM -> NewsViewHolder.create(parent)
            ITEM_VIEW_TYPE_HEADLINE_HEADER -> HeadlinesHeaderViewHolder.create(parent)
            ITEM_VIEW_TYPE_HEADLINE_ITEM -> HeadlinesViewHolder.create(parent)
            else -> throw ClassCastException("Uknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> {
                val topStory = getItem(position) as DataItem.TopStoryItem
                holder.bind(topStory.news)
            }
            is HeadlinesViewHolder -> {
                val headline = getItem(position) as DataItem.HeadlineItem
                holder.bind(headline.news)
            }
        }
    }

    companion object COMPARATOR : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

}

sealed class DataItem {
    abstract val id: String

    object TopStoryHeader : DataItem() {
        override val id: String = "TOP_STORY_HEADER_ITEM"
    }

    data class TopStoryItem(val news: News) : DataItem() {
        override val id: String = news.id
    }

    object HeadlineHeader : DataItem() {
        override val id: String = "HEADLINES_HEADER_ITEM"
    }

    data class HeadlineItem(val news: News) : DataItem() {
        override val id: String = news.id
    }
}

const val ITEM_VIEW_TYPE_TOP_STORY_HEADER = 0
const val ITEM_VIEW_TYPE_TOP_STORY_ITEM = 1
const val ITEM_VIEW_TYPE_HEADLINE_HEADER = 2
const val ITEM_VIEW_TYPE_HEADLINE_ITEM = 3