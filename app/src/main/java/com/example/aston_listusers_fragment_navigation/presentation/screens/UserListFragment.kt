package com.example.aston_listusers_fragment_navigation.presentation.screens

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_listusers_fragment_navigation.AppApplication
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.databinding.FragmentUserListBinding
import com.example.aston_listusers_fragment_navigation.presentation.common.recycler_view.DiffUtilItemCallback
import com.example.aston_listusers_fragment_navigation.presentation.common.recycler_view.user.UserRvAdapter
import com.example.aston_listusers_fragment_navigation.presentation.screens.common.BaseFragment
import com.example.aston_listusers_fragment_navigation.presentation.viewmodels.UserListViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UserListFragment : BaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate) {

    @Inject
    lateinit var viewModel: UserListViewModel

    private val adapterUserList by lazy {
        AsyncListDifferDelegationAdapter(
            DiffUtilItemCallback(),
            UserRvAdapter(::clickByUser)
        )
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun thisActions() {
        viewModel.getUserList()
    }

    override fun thisSettings() {
        settingsUserList()
        observeData()
    }

    private fun observeData() {
        viewModel.userListStateFlow.onEach { updateUserList(it) }.launchIn(lifecycleScope)
    }

    private fun updateUserList(list: List<User>?) {
        if (list == null) return
        adapterUserList.items = list
    }

    private fun settingsUserList() {
        with(binding.userList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterUserList
            setHasFixedSize(true)
        }
    }

    private fun clickByUser(user: User) {
        navController.openEditingUserInfoFragment(user) { newUser: User? ->
            if (newUser == null) return@openEditingUserInfoFragment
            viewModel.updateUserInfo(user, newUser)
        }
    }

    private fun inject() {
        (requireContext().applicationContext as? AppApplication)?.component?.inject(this)
    }

    companion object {
        const val TAG_FRG = "UserListFragment"

        fun newInstance() = UserListFragment()
    }

}