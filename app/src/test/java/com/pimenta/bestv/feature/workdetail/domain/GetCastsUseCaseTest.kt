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

package com.pimenta.bestv.feature.workdetail.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.pimenta.bestv.common.presentation.model.CastViewModel
import com.pimenta.bestv.common.presentation.model.WorkType
import com.pimenta.bestv.data.MediaRepository
import com.pimenta.bestv.data.remote.entity.CastListResponse
import com.pimenta.bestv.data.remote.entity.CastResponse
import io.reactivex.Single
import org.junit.Test

/**
 * Created by marcus on 23-06-2018.
 */
private const val MOVIE_ID = 1
private val CAST_LIST = CastListResponse(
        id = 1,
        casts = listOf(
                CastResponse(
                        id = 1,
                        name = "Name",
                        character = "Character",
                        birthday = "Birthday",
                        deathDay = null,
                        biography = null
                )
        )
)
private val CAST_VIEW_MODELS = listOf(
        CastViewModel(
                id = 1,
                name = "Name",
                character = "Character",
                birthday = "Birthday",
                deathDay = null,
                biography = null
        )
)

class GetCastsUseCaseTest {

    private val mediaRepository: MediaRepository = mock()
    private val useCase = GetCastsUseCase(mediaRepository)

    @Test
    fun `should return the right data when loading the casts`() {
        whenever(mediaRepository.getCastByMovie(MOVIE_ID)).thenReturn(Single.just(CAST_LIST))

        useCase(WorkType.MOVIE, MOVIE_ID)
                .test()
                .assertComplete()
                .assertResult(CAST_VIEW_MODELS)
    }

    @Test
    fun `should return an error when some exception happens`() {
        whenever(mediaRepository.getCastByMovie(MOVIE_ID)).thenReturn(Single.error(Throwable()))

        useCase(WorkType.MOVIE, MOVIE_ID)
                .test()
                .assertError(Throwable::class.java)
    }
}