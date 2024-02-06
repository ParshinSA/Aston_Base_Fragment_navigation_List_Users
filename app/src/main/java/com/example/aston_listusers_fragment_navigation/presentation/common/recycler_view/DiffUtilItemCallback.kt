package com.example.aston_listusers_fragment_navigation.presentation.common.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.presentation.common.interfaces.Displayable

class DiffUtilItemCallback : DiffUtil.ItemCallback<Displayable>() {

    override fun areItemsTheSame(oldItem: Displayable, newItem: Displayable) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Displayable, newItem: Displayable): Boolean {
        val result = when {
            equalsTypes<User>(oldItem, newItem) -> {
                val old = oldItem as User
                val new = newItem as User
                old.toString() == new.toString()
            }

            else -> false
        }
        return result
    }

    private inline fun <reified T : Displayable> equalsTypes(
        oldItem: Displayable,
        newItem: Displayable
    ) = oldItem is T && newItem is T

}