/*
 *   Copyright (C) 2018 Eton Otieno Oboch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.edoubletech.newsfeed.ui.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.data.model.Category

class CategoryAdapter(val mOnClickListener: ListItemClickListener)
    : ListAdapter<Category, CategoryAdapter.NewsViewHolder>(COMPARATOR) {

    companion object {
        val COMPARATOR: DiffUtil.ItemCallback<Category> = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name.equals(newItem.name)
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    interface ListItemClickListener {
        fun onListItemClick(clickedItemIndex: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentCategory = getItem(position)
        holder.textView.text = currentCategory.name
        holder.imageView.setImageResource(currentCategory.image)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.grid_image)
        val textView: TextView = itemView.findViewById(R.id.grid_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val clickedPosition = adapterPosition
            mOnClickListener.onListItemClick(clickedPosition)
        }
    }
}