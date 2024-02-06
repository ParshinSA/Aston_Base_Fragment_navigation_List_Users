package com.example.aston_listusers_fragment_navigation

import android.app.Application
import com.example.aston_listusers_fragment_navigation.common.deps_inject.AppComponent
import com.example.aston_listusers_fragment_navigation.common.deps_inject.DaggerAppComponent

class AppApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

}