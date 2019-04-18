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

package com.pimenta.bestv.common.presentation.mapper

import com.pimenta.bestv.BuildConfig
import com.pimenta.bestv.common.presentation.model.VideoViewModel
import com.pimenta.bestv.repository.entity.Video

fun Video.toViewModel() = VideoViewModel(
        id = id,
        name = name,
        type = type,
        thumbnailUrl = String.format(BuildConfig.YOUTUBE_THUMBNAIL_BASE_URL, key),
        youtubeUrl = String.format(BuildConfig.YOUTUBE_BASE_URL, key)
)

