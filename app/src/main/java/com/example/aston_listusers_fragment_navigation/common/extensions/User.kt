package com.example.aston_listusers_fragment_navigation.common.extensions

import com.example.aston_listusers_fragment_navigation.data.models.User
import com.google.gson.Gson

fun User.toJson(): String {
    return Gson().toJson(this)
}

fun String?.toUserFromJson(): User? {
    if (this == null) return null
    return try {
        Gson().fromJson(this, User::class.java)
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        null
    }
}