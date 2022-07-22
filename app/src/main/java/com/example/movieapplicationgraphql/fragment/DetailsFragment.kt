package com.example.movieapplicationgraphql.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.movieapplicationgraphql.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)

        initViews()
    }

    private fun initViews() {

    }
}