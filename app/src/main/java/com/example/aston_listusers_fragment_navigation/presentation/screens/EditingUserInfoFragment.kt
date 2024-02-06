package com.example.aston_listusers_fragment_navigation.presentation.screens

import android.content.Context
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.aston_listusers_fragment_navigation.AppApplication
import com.example.aston_listusers_fragment_navigation.R
import com.example.aston_listusers_fragment_navigation.common.extensions.toJson
import com.example.aston_listusers_fragment_navigation.common.extensions.toUserFromJson
import com.example.aston_listusers_fragment_navigation.data.models.User
import com.example.aston_listusers_fragment_navigation.databinding.FragmentEditingUserInfoBinding
import com.example.aston_listusers_fragment_navigation.presentation.screens.common.BaseFragment
import com.example.aston_listusers_fragment_navigation.presentation.viewmodels.EditingUserInfoViewModel
import javax.inject.Inject

class EditingUserInfoFragment :
    BaseFragment<FragmentEditingUserInfoBinding>(FragmentEditingUserInfoBinding::inflate) {

    @Inject
    lateinit var viewModel: EditingUserInfoViewModel

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun thisActions() {
        retrieveArguments()
    }

    private fun retrieveArguments() {
        val userStringModel = requireArguments().getString(KEY_USER)
            ?: throw IllegalStateException("Value by key KEY_USER not found")

        val user = userStringModel.toUserFromJson()
            ?: throw IllegalStateException("Failed create object User")

        viewModel.saveOldUser(user)
        setUserData(user)
    }

    override fun thisSettings() {
        settingsDeclineChanges()
        settingsConfirmChanges()
    }

    private fun settingsConfirmChanges() {
        binding.btnUpdate.setOnClickListener {
            val key = viewModel.oldUserStateFlow.value.hashCode().toString()
            val newUser = createNewUser()
            if (newUser == null) throw IllegalStateException("Failed create object User")
            else {
                val newUserStringModel = newUser.toJson()
                sendChanges(Bundle().apply { putString(key, newUserStringModel) })
            }
        }
    }

    private fun createNewUser(): User? {
        return User(
            avatar = viewModel.oldUserStateFlow.value?.avatar ?: return null,
            firstName = binding.textFieldFirstName.editText?.text?.toString() ?: return null,
            lastName = binding.textFieldLastName.editText?.text?.toString() ?: return null,
            phoneNumber = binding.textFieldPhoneNumber.editText?.text?.toString() ?: return null,
        )
    }

    private fun settingsDeclineChanges() {
        binding.btnCancel.setOnClickListener { sendChanges(Bundle()) }
    }

    private fun sendChanges(bundle: Bundle) {
        val key = viewModel.oldUserStateFlow.value.hashCode().toString()
        requireActivity().supportFragmentManager.setFragmentResult(key, bundle)
    }

    private fun setUserData(user: User) {
        Glide.with(this)
            .load(user.avatar)
            .error(R.drawable.ic_image_not_supported)
            .into(binding.avatar)

        binding.textFieldPhoneNumber.editText?.setText(user.phoneNumber)
        binding.textFieldFirstName.editText?.setText(user.firstName)
        binding.textFieldLastName.editText?.setText(user.lastName)
    }

    private fun inject() {
        (requireContext().applicationContext as? AppApplication)?.component?.inject(this)
    }

    companion object {
        private const val KEY_USER = "KEY_USER"

        const val TAG_FRG = "EditingUserInfoFragment"

        fun newInstance(user: User) = EditingUserInfoFragment().apply {
            val userStringModel = user.toJson()
            arguments = Bundle().apply {
                putString(KEY_USER, userStringModel)
            }
        }
    }

}