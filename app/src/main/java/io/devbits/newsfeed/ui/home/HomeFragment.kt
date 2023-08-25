/*
 * Copyright 2020 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.devbits.newsfeed.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.devbits.newsfeed.NewsFeed
import io.devbits.newsfeed.data.model.News
import io.devbits.newsfeed.data.model.Result
import io.devbits.newsfeed.databinding.FragmentHomeBinding
import io.devbits.newsfeed.ui.SpaceItemDecoration
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val newsAdapter = NewsAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<NewsViewModel> { viewModelFactory }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val spaceItemDecoration = SpaceItemDecoration()
            binding.homeNewsRV.addItemDecoration(spaceItemDecoration)
            binding.homeNewsRV.adapter = newsAdapter

            val manager = GridLayoutManager(requireContext(), 2)

            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (newsAdapter.getItemViewType(position)) {
                        ITEM_VIEW_TYPE_HEADLINE_ITEM -> 1
                        else -> 2
                    }
                }
            }
            binding.homeNewsRV.layoutManager = manager

            viewModel.newsLiveData.observe(viewLifecycleOwner, Observer(::handleState))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as NewsFeed).getAppComponent().inject(this)
    }

    private fun handleState(result: Result<List<News>>) {
        when (result) {
            is Result.Loading -> setUpScreenForLoadingState()
            is Result.Success -> setUpScreenForSuccess(result.data)
            is Result.Error -> setUpScreenForError(result.exception)
        }
    }

    private fun setUpScreenForLoadingState() {
        binding.homeLoadingIndicator.visibility = View.VISIBLE
        binding.homeNewsRV.visibility = View.GONE
        binding.homeEmptyView.visibility = View.GONE
    }

    private fun setUpScreenForSuccess(data: List<News>) {
        binding.homeEmptyView.visibility = View.GONE
        binding.homeLoadingIndicator.visibility = View.GONE

        if (data.isNullOrEmpty()) {
            binding.homeEmptyView.visibility = View.VISIBLE
            binding.homeEmptyView.text = "No Data was found 😑😑"
        } else {
            binding.homeNewsRV.visibility = View.VISIBLE
            newsAdapter.addHeaderAndSubmitList(data)
        }
    }

    private fun setUpScreenForError(exception: Exception) {
        binding.homeLoadingIndicator.visibility = View.GONE
        binding.homeNewsRV.visibility = View.GONE
        binding.homeEmptyView.visibility = View.VISIBLE
        binding.homeEmptyView.text = exception.localizedMessage
    }
}
