package com.lutalic.luboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lutalic.luboard.databinding.FragmentMainPageBinding
import com.lutalic.luboard.core.views.BaseFragment
import com.lutalic.luboard.core.views.BaseScreen
import com.lutalic.luboard.core.views.screenViewModel
import kotlinx.parcelize.Parcelize

class MainPageFragment : BaseFragment() {

    class Screen : BaseScreen
    private lateinit var binding : FragmentMainPageBinding

    override val viewModel by screenViewModel<MainPageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

}