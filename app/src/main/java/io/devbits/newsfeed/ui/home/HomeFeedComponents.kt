/*
 * Copyright 2023 Eton Otieno
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

@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package io.devbits.newsfeed.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.newsfeed.ui.settings.ui.theme.NewsFeedTheme

@Composable
fun HomeFeedTab() {
    TopAppBar(
        title = { Text(text = "Feed") },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
        actions = {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun BreakingNewsTitle(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Breaking News")
            Text(text = "View all", color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreakingNewsCarousel() {
    var tabIndex by remember { mutableStateOf(0) }
    val pagerState: PagerState = rememberPagerState()

    Box {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp,
            pageCount = 5,
        ) { page ->

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    modifier: Modifier,
    pagerState: PagerState,
) {
    val pageCount = 5
    val selectedColor = MaterialTheme.colorScheme.secondary
    val unselectedColor = Color(0xFFD7EFFE)
    val grey = Color.Gray

    Row(modifier = modifier) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) selectedColor else unselectedColor
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
fun HomeFeedTopPreview() {
    NewsFeedTheme {
        HomeFeedTab()
    }
}

@Preview
@Composable
fun BreakingNewsTitlePreview() {
    NewsFeedTheme {
        BreakingNewsTitle()
    }
}

@Preview
@Composable
fun BreakingNewsTabLayoutPreview() {
    NewsFeedTheme {
        BreakingNewsCarousel()
    }
}
