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

import com.pimenta.bestv.common.presentation.mapper.toViewModel
import com.pimenta.bestv.common.presentation.model.WorkPageViewModel
import com.pimenta.bestv.common.presentation.model.WorkType
import com.pimenta.bestv.data.MediaRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marcus on 18-04-2019.
 */
class GetRecommendationByWorkUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {

    operator fun invoke(workType: WorkType, workId: Int, page: Int): Single<WorkPageViewModel> =
            when (workType) {
                WorkType.MOVIE -> mediaRepository.getRecommendationByMovie(workId, page)
                        .map {
                            WorkPageViewModel(
                                    it.page,
                                    it.totalPages,
                                    it.works?.map { work -> work.toViewModel() }
                            )
                        }
                WorkType.TV_SHOW -> mediaRepository.getRecommendationByTvShow(workId, page)
                        .map {
                            WorkPageViewModel(
                                    it.page,
                                    it.totalPages,
                                    it.works?.map { work -> work.toViewModel() }
                            )
                        }
            }
}