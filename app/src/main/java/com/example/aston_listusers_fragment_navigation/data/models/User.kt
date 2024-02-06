package com.example.aston_listusers_fragment_navigation.data.models

import androidx.annotation.DrawableRes
import com.example.aston_listusers_fragment_navigation.presentation.common.interfaces.Displayable

data class User(
    @DrawableRes val avatar: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    override val id: Int = (firstName + lastName).hashCode()
) : Displayable
