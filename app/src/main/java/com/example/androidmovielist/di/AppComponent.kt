package com.example.androidmovielist.di

import com.example.androidmovielist.MoviesApplication
import dagger.Component

import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ActivityBinding::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<MoviesApplication> {
//
//    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<MoviesApplication>()
}