/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.skplayer.core.di

import com.skplayer.AndroidApplication
import com.skplayer.core.di.viewmodel.ViewModelModule
import com.skplayer.features.movies.MovieDetailsFragment
import com.skplayer.features.movies.MoviesFragment
import com.skplayer.core.navigation.RouteActivity
import com.skplayer.features.player.FolderFragment
import com.skplayer.features.player.PlayerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
    fun inject(folderFragment: FolderFragment)
    fun inject(playerFragment: PlayerFragment)
}
