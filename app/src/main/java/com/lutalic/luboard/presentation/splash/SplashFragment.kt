package com.lutalic.luboard.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lutalic.luboard.R
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.databinding.FragmentSplashBinding
import com.lutalic.luboard.presentation.main.MainActivity
import com.lutalic.luboard.presentation.main.MainActivityArgs
import com.lutalic.luboard.utils.observeEvent
import com.lutalic.luboard.utils.viewModelCreator

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    private val viewModel by viewModelCreator { SplashViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        renderAnimations()

        viewModel.launchMainScreenEvent.observeEvent(viewLifecycleOwner) {
            launchMainScreen(it)
        }
    }

    private fun launchMainScreen(isSignedIn: Boolean) {
        val intent = Intent(requireContext(), MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val args = MainActivityArgs(isSignedIn)
        intent.putExtras(args.toBundle())
        startActivity(intent)
    }

    /**
     * Анимация для загрузочного колечка
     */
    private fun renderAnimations() {
        binding.loadingIndicator.alpha = 0f
        binding.loadingIndicator.animate()
            .alpha(0.7f)
            .setDuration(1000)
            .start()

        binding.pleaseWaitTextView.alpha = 0f
        binding.pleaseWaitTextView.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(1000)
            .start()

    }
}