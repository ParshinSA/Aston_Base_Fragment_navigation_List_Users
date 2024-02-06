package com.example.aston_listusers_fragment_navigation.presentation.common.interfaces

import com.example.aston_listusers_fragment_navigation.data.models.User

interface EditingUserInfoFragmentNavController {
    fun openEditingUserInfoFragment(user: User, resultEditing: (User?) -> Unit)
}