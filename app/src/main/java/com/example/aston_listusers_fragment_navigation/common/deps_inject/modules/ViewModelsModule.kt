package com.example.aston_listusers_fragment_navigation.common.deps_inject.modules

import com.example.aston_listusers_fragment_navigation.presentation.common.dependencies.UserRepository
import com.example.aston_listusers_fragment_navigation.presentation.viewmodels.EditingUserInfoViewModel
import com.example.aston_listusers_fragment_navigation.presentation.viewmodels.UserListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelsModule {

    @Provides
    @Singleton
    fun providesUserListViewModule(repository: UserRepository): UserListViewModel {
        return UserListViewModel(repository)
    }

    @Provides
    fun providesEditingUserInfoViewModule(): EditingUserInfoViewModel {
        return EditingUserInfoViewModel()
    }

}