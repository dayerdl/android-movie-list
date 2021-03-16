
package com.example.androidmovielist.di

import android.content.Context
import com.example.androidmovielist.MoviesApplication
import dagger.BindsInstance
import dagger.Component

import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ActivityBinding::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<MoviesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MoviesApplication): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}