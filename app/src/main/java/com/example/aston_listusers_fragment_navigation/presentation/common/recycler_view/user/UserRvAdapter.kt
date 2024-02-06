package com.example.aston_listusers_fragment_navigation.presentation.common.recycler_view.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aston_listusers_fragment_navigation.R
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.databinding.ItemUserBinding
import com.example.aston_listusers_fragment_navigation.presentation.common.interfaces.Displayable
import com.example.aston_listusers_fragment_navigation.presentation.common.recycler_view.user.UserRvAdapter.UserViewHolder
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class UserRvAdapter(
    private val clickByUser: (User) -> Unit,
) : AbsListItemAdapterDelegate<User, Displayable, UserViewHolder>() {


    override fun isForViewType(item: Displayable, items: MutableList<Displayable>, position: Int) =
        item is User

    override fun onCreateViewHolder(parent: ViewGroup): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(clickByUser, view)
    }

    override fun onBindViewHolder(
        item: User, holder: UserViewHolder, payloads: MutableList<Any>
    ) = holder.bind(item)

    class UserViewHolder(
        private val clickByUser: (User) -> Unit,
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemUserBinding.bind(view)
        private val resources = view.context.resources

        private lateinit var user: User

        init {
            binding.userContainer.setOnClickListener {
                clickByUser.invoke(user)
            }
        }

        fun bind(contactItemRv: User) {
            user = contactItemRv
            setAvatar()
            setFullName()
            setPhoneNumber()
        }

        private fun setAvatar() {
            Glide.with(itemView)
                .load(user.avatar)
                .error(R.drawable.ic_image_not_supported)
                .into(binding.avatar)
        }

        private fun setPhoneNumber() {
            binding.phoneNumber.text = user.phoneNumber
        }

        private fun setFullName() {
            with(user) {
                binding.fullName.text = resources.getString(R.string.fullName, firstName, lastName)
            }
        }

    }

}
