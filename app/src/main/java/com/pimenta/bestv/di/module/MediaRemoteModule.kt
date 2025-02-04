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

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pimenta.bestv.BuildConfig
import com.pimenta.bestv.data.remote.api.CastApi
import com.pimenta.bestv.data.remote.api.GenreApi
import com.pimenta.bestv.data.remote.api.MovieApi
import com.pimenta.bestv.data.remote.api.TvShowApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by marcus on 11-05-2018.
 */
@Module
class MediaRemoteModule {

    @Provides
    @Singleton
    fun provideTmdbRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            connectTimeout(15, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit) =
            retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun providePersonGenreApi(retrofit: Retrofit) =
            retrofit.create(GenreApi::class.java)

    @Provides
    @Singleton
    fun provideCastApi(retrofit: Retrofit) =
            retrofit.create(CastApi::class.java)

    @Provides
    @Singleton
    fun provideTvShowApi(retrofit: Retrofit) =
            retrofit.create(TvShowApi::class.java)
}