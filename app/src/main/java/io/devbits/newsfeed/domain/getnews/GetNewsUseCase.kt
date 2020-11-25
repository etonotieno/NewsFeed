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

package io.devbits.newsfeed.domain.getnews

import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.data.Result
import io.devbits.newsfeed.domain.BaseUseCase
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) : BaseUseCase<GuardianNewsParams, List<News>>() {

    override suspend fun invoke(params: GuardianNewsParams): Result<List<News>> {
        return repository.getNewsResults(params.section)
    }
}

class GetGuardianNewsById @Inject constructor(
    private val repository: NewsRepository
) : BaseUseCase<String, News>() {

    override suspend fun invoke(params: String): Result<News> {
        return repository.getNewsById(params)
    }

}