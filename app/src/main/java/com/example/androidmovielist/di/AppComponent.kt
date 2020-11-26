package com.example.androidmovielist.di

import com.example.androidmovielist.ui.activity.MainActivity
import dagger.Component

@Component
interface AppComponent {

    fun inject(activity: MainActivity)
}