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

package io.devbits.newsfeed.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.NewsFeedSampleData
import io.devbits.newsfeed.ui.settings.ui.theme.NewsFeedTheme

@Composable
fun BreakingNewsItem(newsModel: News, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(200.dp)
    ) {
        AsyncImage(
            newsModel.imageUrl,
            contentDescription = null,
        )

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(color = Color.Gray.copy(alpha = .8f))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = newsModel.sectionName,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            )

            Row {
                Text(text = newsModel.source)
                Text(text = newsModel.publicationDate)
            }

            Text(text = newsModel.title)
        }
    }
}

@Preview
@Composable
fun BreakingNewsItemPreview() {
    NewsFeedTheme {
        BreakingNewsItem(NewsFeedSampleData[1])
    }
}