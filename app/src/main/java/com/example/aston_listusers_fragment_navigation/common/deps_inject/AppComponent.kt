package com.example.aston_listusers_fragment_navigation.common.deps_inject

import android.content.Context
import com.example.aston_listusers_fragment_navigation.common.deps_inject.modules.RepositoryModule
import com.example.aston_listusers_fragment_navigation.common.deps_inject.modules.ViewModelsModule
import com.example.aston_listusers_fragment_navigation.presentation.screens.EditingUserInfoFragment
import com.example.aston_listusers_fragment_navigation.presentation.screens.UserListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ViewModelsModule::class,
    ]
)
interface AppComponent {

    fun inject(fragment: EditingUserInfoFragment)
    fun inject(fragment: UserListFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}