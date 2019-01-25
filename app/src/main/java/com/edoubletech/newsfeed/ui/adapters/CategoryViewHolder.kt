package com.edoubletech.newsfeed.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.edoubletech.newsfeed.data.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryViewHolder(categoryItemView: View) : RecyclerView.ViewHolder(categoryItemView) {

    fun bind(category: Category, onItemClick: (category: Category) -> Unit) {
        itemView.grid_name.text = category.name
        itemView.grid_image.setImageResource(category.image)

        itemView.setOnClickListener {
            onItemClick(category)
        }
    }
}