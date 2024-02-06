package com.example.aston_listusers_fragment_navigation.presentation.screens.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.aston_listusers_fragment_navigation.presentation.common.interfaces.EditingUserInfoFragmentNavController

abstract class BaseFragment<BaseFrgViewBinding : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> BaseFrgViewBinding
) : Fragment() {

    private var _binding: BaseFrgViewBinding? = null
    protected val binding get() = checkNotNull(_binding)

    protected val navController by lazy {
        (parentFragment as? EditingUserInfoFragmentNavController)
            ?: (requireActivity() as? EditingUserInfoFragmentNavController)
            ?: throw RuntimeException("Class implements \"EditingUserInfoFragmentNavController\" not fond")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            thisSettings()
            thisActions()
        } else thisSettings()
    }

    abstract fun thisActions()

    abstract fun thisSettings()

}