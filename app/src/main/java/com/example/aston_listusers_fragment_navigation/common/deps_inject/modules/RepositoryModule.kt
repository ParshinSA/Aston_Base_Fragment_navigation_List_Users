package com.example.aston_listusers_fragment_navigation.common.deps_inject.modules

import android.content.Context
import com.example.aston_listusers_fragment_navigation.data.repositories.UserRepositoryImpl
import com.example.aston_listusers_fragment_navigation.presentation.common.dependencies.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryImpl(context)
    }

}