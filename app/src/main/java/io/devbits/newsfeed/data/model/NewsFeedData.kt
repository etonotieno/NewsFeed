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

package io.devbits.newsfeed.data.model

val NewsFeedSampleData = listOf(
    News(
        id = "1",
        imageUrl = "https://d32r1sh890xpii.cloudfront.net/article/718x300/2023-08-01_xluow12vdy.jpg",
        webUrl = "https://oilprice.com/Energy/Energy-General/Chinas-Economic-Woes-Weigh-On-Oil-Prices-Once-Again.html",
        sectionName = "Economy",
        title = "China's Economic Woes Weigh On Oil Prices Once Again",
        body = "The weakening of the US…\r\nOil markets have always struggled…\r\nAfrica faces both significant challenges…\r\nBy Michael Kern - Aug 01, 2023, 9:00 AM CDTAfter climbing by 13% in July, oil prices were drag… [+6069 chars]",
        publicationDate = "2023-08-01T14:00:00Z",
        source = "OilPrice.com",
        summary = "After climbing by 13% in July, oil prices were dragged lower on the first day of August by further disappointing economic data out of China. Chart of the Week- Boosting the effect of Saudi Arabia’s production cuts, oil production in Canada’s crude heartland A…",
        origin = Origin.NEWS_API,
    ),
    News(
        id = "2",
        imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiNvGkqpl70Z4ObkPIVxV9P7yny2cBrCtSEUHQF5sLdIPipymfq4rWKGQea7zr5ky9IbBX3seWci1GVkV_iO-2r2d66tl0ISAeju7GXXMOE1KklJMLNZzQTHC-L4zHZK5lYqEAf0_ki1B2ECMI-VfzCeJ3aTAIGAOjGgF1UHsCM2RibfrZggQlF3_3z/s1600/image1.gif",
        webUrl = "https://android-developers.googleblog.com/",
        sectionName = "Technology",
        title = "This is the first phone with a 64-megapixel camera",
        body = "Simon Henderson to appear before education committee after Guardian reported exam questions had been leaked. This is a placeholder text to know how the UI will change according to the change in text.Simon Henderson to appear before education committee after Guardian reported exam questions had been leaked. This is a placeholder text to know how the UI will change according to the change in text.Simon Henderson to appear before education committee after Guardian reported exam questions had been leaked. This is a placeholder text to know how the UI will change according to the change in text.Simon Henderson to appear before education committee after Guardian reported exam questions had been leaked. This is a placeholder text to know how the UI will change according to the change in text.",
        publicationDate = "22/10/2019",
        source = "Tech News",
        summary = "This is the first phone with a 64-megapixel camera",
        origin = Origin.NEWS_API,
    )
)
