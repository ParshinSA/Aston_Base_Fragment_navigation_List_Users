package com.example.aston_listusers_fragment_navigation.presentation.common.dependencies

import com.example.aston_listusers_fragment_navigation.data.models.User

interface UserRepository {

    fun getUserList(): List<User>

}
