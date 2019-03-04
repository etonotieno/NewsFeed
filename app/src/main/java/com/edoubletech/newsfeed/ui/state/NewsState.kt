package com.edoubletech.newsfeed.ui.state

import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.ui.state.NewsState.*

/**
 * This class holds the View state for the app using a sealed class.
 * There are 3 states:
 * [Loading], [Success] and [Error]
 */
sealed class NewsState(resourceState: ResourceState, val data: List<News>? = null,
                       val error: String? = null) {

    object Loading : NewsState(ResourceState.LOADING)

    data class Success(private val newsList: List<News>) : NewsState(ResourceState.SUCCESS,
            newsList)

    data class Error(private val message: String) : NewsState(ResourceState.ERROR, error = message)
}