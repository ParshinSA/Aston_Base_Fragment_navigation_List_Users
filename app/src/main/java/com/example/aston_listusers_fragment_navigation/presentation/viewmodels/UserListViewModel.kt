package com.example.aston_listusers_fragment_navigation.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.presentation.common.dependencies.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserListViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userListMutStateFlow = MutableStateFlow<List<User>?>(null)
    val userListStateFlow get() = userListMutStateFlow.asStateFlow()

    fun getUserList() {
        userListMutStateFlow.value = userRepository.getUserList()
    }

    fun updateUserInfo(oldUser: User, newUser: User) {
        userListMutStateFlow.value = userListMutStateFlow.value?.map { user: User ->
            if (user != oldUser) user
            else newUser.copy(id = oldUser.id)
        }
    }

}