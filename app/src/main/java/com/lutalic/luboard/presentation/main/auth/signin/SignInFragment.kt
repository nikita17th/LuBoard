package com.lutalic.luboard.presentation.main.auth.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lutalic.luboard.R
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.presentation.uiactions.AndroidUiActions
import com.lutalic.luboard.databinding.FragmentSignInBinding
import com.lutalic.luboard.utils.observeEvent
import com.lutalic.luboard.utils.viewModelCreator

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel by viewModelCreator {
        SignInViewModel(
            Repositories.accountsRepository,
            AndroidUiActions(appContext = requireContext())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.signInButton.setOnClickListener { onSignInButtonPressed() }
        binding.signUpButton.setOnClickListener { onSignUpButtonPressed() }

        observeState()
        observeClearPasswordEvent()
        observeNavigateToTabsEvent()
        observeValidateEmail()
    }

    private fun onSignInButtonPressed() {
        viewModel.signIn(
            email = binding.emailEditText.text.toString(),
            password = binding.passwordEditText.text.toString()
        )
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) {
        binding.emailTextInput.error =
            if (it.emptyEmailError) getString(R.string.field_is_empty) else null
        binding.passwordTextInput.error =
            if (it.emptyPasswordError) getString(R.string.field_is_empty) else null

        binding.emailTextInput.isEnabled = it.enableViews
        binding.passwordTextInput.isEnabled = it.enableViews
        binding.signInButton.isEnabled = it.enableViews
        binding.signUpButton.isEnabled = it.enableViews
        binding.progressBar.visibility = if (it.showProgress) View.VISIBLE else View.INVISIBLE
    }


    private fun observeClearPasswordEvent() =
        viewModel.clearPasswordEvent.observeEvent(viewLifecycleOwner) {
            binding.passwordEditText.text?.clear()
        }

    private fun observeValidateEmail() =
        viewModel.validateEmailEvent.observeEvent(viewLifecycleOwner) {
            ValidateErrorDialogFragment().show(childFragmentManager,
                ValidateErrorDialogFragment.TAG
            )
        }


    private fun observeNavigateToTabsEvent() =
        viewModel.navigateToTabsEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
        }

    private fun onSignUpButtonPressed() {
        val email = binding.emailEditText.text.toString()
        val emailArg = email.ifBlank { null }

        val direction = SignInFragmentDirections.actionSignInFragmentToSignUpFragment(emailArg)
        findNavController().navigate(direction)
    }
}