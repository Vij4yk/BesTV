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

package com.pimenta.bestv.di.module

import android.app.Application
import android.app.NotificationManager
import android.os.Build
import com.pimenta.bestv.data.local.provider.channel.RecommendationChannelApi
import com.pimenta.bestv.data.local.provider.row.RecommendationRowApi
import com.pimenta.bestv.data.local.sharedpreferences.LocalSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by marcus on 23-04-2019.
 */
@Module
class RecommendationModule {

    @Provides
    @Singleton
    fun provideRecommendationManager(
        application: Application,
        localSettings: LocalSettings,
        notificationManager: NotificationManager
    ) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                RecommendationChannelApi(application, localSettings)
            else
                RecommendationRowApi(application, notificationManager)
}