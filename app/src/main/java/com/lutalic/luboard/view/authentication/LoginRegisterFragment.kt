package com.lutalic.luboard.view.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lutalic.luboard.databinding.FragmentLoginRegistrationBinding
import com.lutalic.luboard.core.views.BaseFragment
import com.lutalic.luboard.core.views.BaseScreen
import com.lutalic.luboard.core.views.screenViewModel
import kotlinx.parcelize.Parcelize

private const val TAG = "LoginRegisterFragment"

class LoginRegisterFragment : BaseFragment() {

    override val viewModel by screenViewModel<LoginRegisterViewModel>()
    private lateinit var binding: FragmentLoginRegistrationBinding

    class Screen : BaseScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginRegistrationBinding.inflate(inflater, container, false)
        binding.singUpButton.setOnClickListener {
            viewModel.register(
                binding.emailEdit.text.toString(), binding.passwordEdit.text.toString()
            )
        }
        binding.singInButton.setOnClickListener {
            viewModel.login(
                binding.emailEdit.text.toString(), binding.passwordEdit.text.toString()
            )
        }
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                Log.d(TAG, user.email!!)
                viewModel.registrationSuccess()
            }
        }
        return binding.root
    }
}