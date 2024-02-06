package com.example.aston_listusers_fragment_navigation.data.repositories

import android.content.Context
import com.example.aston_listusers_fragment_navigation.R
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.presentation.common.dependencies.UserRepository
import kotlin.random.Random

class UserRepositoryImpl(
    private val context: Context,
) : UserRepository {

    private var userList: List<User>? = null

    override fun getUserList(): List<User> {
        if (userList == null) createContactBook()
        return checkNotNull(userList)
    }

    private fun createContactBook() {
        val listFirstNames = context.resources.getStringArray(R.array.firstNames)
        val listLastNames = context.resources.getStringArray(R.array.lastNames)
        val listAvatars = listOf(
            R.drawable.ic_avatar_1,
            R.drawable.ic_avatar_2,
            R.drawable.ic_avatar_3,
            R.drawable.ic_avatar_4,
            R.drawable.ic_avatar_5,
        )
        val newUserList = mutableListOf<User>()

        repeat(100) {
            newUserList.add(
                User(
                    avatar = listAvatars.random(),
                    firstName = listFirstNames.random(),
                    lastName = listLastNames.random(),
                    phoneNumber = "+7" +
                            "(${Random.nextInt(100, 999)})" +
                            "-${Random.nextInt(100, 999)}" +
                            "-${Random.nextInt(10, 99)}" +
                            "-${Random.nextInt(10, 99)}"
                )
            )
        }

        userList = newUserList
    }

}