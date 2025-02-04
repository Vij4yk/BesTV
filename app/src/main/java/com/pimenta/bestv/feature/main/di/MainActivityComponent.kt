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

package com.pimenta.bestv.feature.main.di

import android.app.Application
import com.pimenta.bestv.di.module.ApplicationModule
import com.pimenta.bestv.di.module.DisplayMetricsModule
import com.pimenta.bestv.di.module.MediaModule
import com.pimenta.bestv.di.module.SchedulerModule
import com.pimenta.bestv.feature.main.presentation.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by marcus on 2019-08-29.
 */
@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            DisplayMetricsModule::class,
            MediaModule::class,
            SchedulerModule::class
        ]
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): MainActivityComponent
    }

    companion object {
        fun create(application: Application): MainActivityComponent =
                DaggerMainActivityComponent.factory()
                        .create(application)
    }
}