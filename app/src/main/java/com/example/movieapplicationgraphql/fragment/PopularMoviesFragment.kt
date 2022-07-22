package com.example.movieapplicationgraphql.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.movieapplicationgraphql.R
import com.example.movieapplicationgraphql.databinding.FragmentPopularMoviesBinding

class PopularMoviesFragment : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var binding: FragmentPopularMoviesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPopularMoviesBinding.bind(view)

        initViews()
    }

    private fun initViews() {

    }
}