package com.example.aston_listusers_fragment_navigation.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_listusers_fragment_navigation.R
import com.example.aston_listusers_fragment_navigation.common.extensions.toUserFromJson
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.databinding.ActivityAppBinding
import com.example.aston_listusers_fragment_navigation.presentation.common.interfaces.EditingUserInfoFragmentNavController
import com.example.aston_listusers_fragment_navigation.presentation.screens.EditingUserInfoFragment
import com.example.aston_listusers_fragment_navigation.presentation.screens.UserListFragment

class AppActivity : AppCompatActivity(), EditingUserInfoFragmentNavController {

    private var _binding: ActivityAppBinding? = null
    val binding get() = checkNotNull(_binding)

    private val container by lazy { R.id.containerAppActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) openUserListFragment()
    }

    private fun openUserListFragment() {
        binding.appToolbar.title = getString(R.string.title_user_list)

        supportFragmentManager.beginTransaction()
            .add(container, UserListFragment.newInstance(), UserListFragment.TAG_FRG )
            .addToBackStack(UserListFragment.TAG_FRG)
            .commit()
    }

    override fun openEditingUserInfoFragment(user: User, resultEditing: (User?) -> Unit) {
        binding.appToolbar.title = getString(R.string.title_editing_user_info)

        val previousFragment = supportFragmentManager.fragments.first { it.isVisible }
        val editingUserInfoFragment = EditingUserInfoFragment.newInstance(user)
        val responseKey = user.hashCode().toString()

        supportFragmentManager.setFragmentResultListener(responseKey, this) { key, bundle ->
            if (key != responseKey) return@setFragmentResultListener
            supportFragmentManager.clearFragmentResult(responseKey)
            val newUserStringModel = bundle.getString(key)
            val newUser = newUserStringModel.toUserFromJson()
            resultEditing.invoke(newUser)

            supportFragmentManager.popBackStack()
        }

        supportFragmentManager.beginTransaction()
            .add(container, editingUserInfoFragment)
            .addToBackStack(EditingUserInfoFragment.TAG_FRG)
            .hide(previousFragment)
            .commit()
    }

}