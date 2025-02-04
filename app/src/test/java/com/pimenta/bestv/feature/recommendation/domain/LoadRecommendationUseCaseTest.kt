/*
 * Copyright (C) 2018 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.bestv.feature.recommendation.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pimenta.bestv.common.presentation.model.WorkPageViewModel
import com.pimenta.bestv.common.presentation.model.WorkType
import com.pimenta.bestv.common.presentation.model.WorkViewModel
import com.pimenta.bestv.data.MediaRepository
import com.pimenta.bestv.data.remote.entity.MoviePageResponse
import com.pimenta.bestv.data.remote.entity.MovieResponse
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test

/**
 * Created by marcus on 2019-08-27.
 */
private val WORK_PAGE = MoviePageResponse().apply {
    page = 1
    totalPages = 1
    works = listOf(
            MovieResponse(
                    id = 1,
                    title = "Batman",
                    originalTitle = "Batman"
            )
    )
}
private val MOVIE_PAGE_VIEW_MODEL = WorkPageViewModel(
        page = 1,
        totalPages = 1,
        works = listOf(
                WorkViewModel(
                        id = 1,
                        title = "Batman",
                        originalTitle = "Batman",
                        type = WorkType.MOVIE
                )
        )
)

class LoadRecommendationUseCaseTest {

    private val mediaRepository: MediaRepository = mock()
    private val useCase = LoadRecommendationUseCase(
            mediaRepository
    )

    @Test
    fun `should return the right data when loading the recommendations`() {
        whenever(mediaRepository.loadWorkByType(1, MediaRepository.WorkType.POPULAR_MOVIES))
                .thenReturn(Single.just(WORK_PAGE))
        whenever(mediaRepository.loadRecommendations(MOVIE_PAGE_VIEW_MODEL.works))
                .thenReturn(Completable.complete())

        useCase()
                .test()
                .assertComplete()

        verify(mediaRepository).loadRecommendations(MOVIE_PAGE_VIEW_MODEL.works)
    }

    @Test
    fun `should return an error when some exception happens`() {
        whenever(mediaRepository.loadWorkByType(1, MediaRepository.WorkType.POPULAR_MOVIES))
                .thenReturn(Single.error(Throwable()))

        useCase()
                .test()
                .assertError(Throwable::class.java)
    }
}