package com.lutalic.luboard.presentation.main.tabs.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lutalic.luboard.R
import com.lutalic.luboard.databinding.FragmentPostsBinding
import com.lutalic.luboard.utils.viewModelCreator

class PostsFragment : Fragment(R.layout.fragment_posts) {

    private lateinit var binding: FragmentPostsBinding

    private val viewModel by viewModelCreator { PostsViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostsBinding.bind(view)
    }
}