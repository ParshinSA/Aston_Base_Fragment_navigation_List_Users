package com.example.aston_listusers_fragment_navigation.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.aston_listusers_fragment_navigation.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditingUserInfoViewModel : ViewModel() {

    private val oldUserMutStateFlow = MutableStateFlow<User?>(null)
    val oldUserStateFlow get() = oldUserMutStateFlow.asStateFlow()

    fun saveOldUser(user: User) {
        oldUserMutStateFlow.value = user
    }

}
