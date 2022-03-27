package com.lutalic.luboard.presentation.main.auth

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputLayout
import com.lutalic.luboard.core.uiactions.AndroidUiActions
import com.lutalic.luboard.R
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.databinding.FragmentSignUpBinding
import com.lutalic.luboard.model.accounts.entities.SignUpData
import com.lutalic.luboard.utils.observeEvent
import com.lutalic.luboard.utils.viewModelCreator

class SignUpFragment() : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel by viewModelCreator {
        SignUpViewModel(
            Repositories.accountsRepository,
            AndroidUiActions(requireContext())
        )
    }

    private val args by navArgs<SignUpFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.createAccountButton.setOnClickListener { onCreateAccountButtonPressed() }

        if (savedInstanceState == null && getEmailArgument() != null) {
            binding.emailEditText.setText(getEmailArgument())
        }

        observeState()
        observeGoBackEvent()
        observeShowSuccessSignUpMessageEvent()
    }

    private fun onCreateAccountButtonPressed() {
        val signUpData = SignUpData(
            email = binding.emailEditText.text.toString(),
            password = binding.passwordEditText.text.toString(),
            repeatPassword = binding.repeatPasswordEditText.text.toString(),
        )
        viewModel.signUp(signUpData)
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) { state ->
        binding.createAccountButton.isEnabled = state.enableViews
        binding.emailTextInput.isEnabled = state.enableViews
        binding.passwordTextInput.isEnabled = state.enableViews
        binding.repeatPasswordTextInput.isEnabled = state.enableViews

        fillError(binding.emailTextInput, state.emailErrorMessageRes)
        fillError(binding.passwordTextInput, state.passwordErrorMessageRes)
        fillError(binding.repeatPasswordTextInput, state.repeatPasswordErrorMessageRes)

        binding.progressBar.visibility = if (state.showProgress) View.VISIBLE else View.INVISIBLE
    }

    private fun observeShowSuccessSignUpMessageEvent() {
        viewModel.showSuccessSignUpMessageEvent.observeEvent(viewLifecycleOwner) {
            viewModel.toastSingUpSuccess(resources.getString(R.string.sign_up_success))
        }
    }

    private fun fillError(input: TextInputLayout, @StringRes stringRes: Int) {
        if (stringRes == SignUpViewModel.NO_ERROR_MESSAGE) {
            input.error = null
            input.isErrorEnabled = false
        } else {
            input.error = getString(stringRes)
            input.isErrorEnabled = true
        }
    }

    private fun observeGoBackEvent() = viewModel.goBackEvent.observeEvent(viewLifecycleOwner) {
        findNavController().popBackStack()
    }

    private fun getEmailArgument(): String? = args.email
}