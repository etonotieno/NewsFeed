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

package io.devbits.newsfeed.ui.newsdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import io.devbits.newsfeed.R
import io.devbits.newsfeed.data.model.News
import io.devbits.newsfeed.data.model.NewsFeedSampleData
import io.devbits.newsfeed.data.model.Origin
import io.devbits.newsfeed.ui.settings.ui.theme.NewsFeedTheme

@Composable
fun NewsDetailScreen(
    news: News,
    modifier: Modifier = Modifier,
    onGoToWebsite: (uri: String) -> Unit = {},
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = news.imageUrl,
            contentDescription = stringResource(id = R.string.article_poster_cd)
        )

        Text(
            text = news.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        val body: String = when (news.origin) {
            Origin.GUARDIAN_API -> news.body?.let {
                HtmlCompat.fromHtml(
                    it,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                ).toString()
            }.orEmpty()

            Origin.NEWS_API -> news.body.orEmpty()
        }

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = body, textAlign = TextAlign.Justify)

        Spacer(modifier = Modifier.size(8.dp))

        OutlinedButton(
            onClick = { onGoToWebsite(news.webUrl) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text(text = stringResource(id = R.string.go_to_website_string))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsDetailScreenPreview() {
    NewsFeedTheme {
        NewsDetailScreen(NewsFeedSampleData[0])
    }
}
