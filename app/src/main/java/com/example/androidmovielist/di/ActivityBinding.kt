package com.example.androidmovielist.di

import com.example.androidmovielist.ui.activity.MainActivity
import com.example.moviedetail.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinding {

    @ContributesAndroidInjector(modules =[MoviesModuleRepository::class])
    abstract fun movieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity

}