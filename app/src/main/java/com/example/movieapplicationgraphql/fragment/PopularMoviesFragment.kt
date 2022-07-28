package com.example.movieapplicationgraphql.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.movieapplicationgraphql.PopularMoviesQuery
import com.example.movieapplicationgraphql.R
import com.example.movieapplicationgraphql.adapter.TVShowAdapter
import com.example.movieapplicationgraphql.databinding.FragmentPopularMoviesBinding
import com.example.movieapplicationgraphql.model.TVShow
import com.example.movieapplicationgraphql.utils.Visibility.hide
import com.example.movieapplicationgraphql.utils.Visibility.show
import com.example.movieapplicationgraphql.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PopularMoviesFragment : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var binding: FragmentPopularMoviesBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sendRequestToGetPopular()
    }

    private fun sendRequestToGetPopular() {
        viewModel.apiGetPopularMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPopularMoviesBinding.bind(view)
        postponeEnterTransition(1000L, TimeUnit.MICROSECONDS)
        binding.rvPopularMovies.post { startPostponedEnterTransition() }

        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)


        initViews()
    }

    private fun initViews() {
        initObservers()

        refreshAdapter(arrayListOf())

        binding.btnFab.setOnClickListener {
            binding.rvPopularMovies.smoothScrollToPosition(0)
        }
    }

    private fun refreshAdapter(movies: List<PopularMoviesQuery.Movie>) {
        adapter = TVShowAdapter(movies)
        binding.rvPopularMovies.adapter = adapter

        adapter.onClick = { movieData, img ->

            saveToDB(movieData)

            openDetailsFragment(movieData, img)
        }
    }

    private fun saveToDB(movieData: PopularMoviesQuery.Movie) {
        viewModel.saveToDB(
            TVShow(
                movieData.id,
                movieData.original_title,
                movieData.release_date,
                movieData.poster_path
            )
        )
    }

    private fun openDetailsFragment(tvShow: PopularMoviesQuery.Movie, ivMovie: ImageView) {
        findNavController().navigate(
            R.id.action_popularMoviesFragment_to_detailsFragment,
            bundleOf(
                "show_name" to tvShow.original_title,
                "show_img" to tvShow.poster_path,
                "show_network" to tvShow.release_date,
                "iv_movie" to ViewCompat.getTransitionName(ivMovie)
            ),
            null,
            FragmentNavigatorExtras(
                ivMovie to ViewCompat.getTransitionName(ivMovie)!!
            )
        )
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbLoading.show()
            } else {
                binding.pbLoading.hide()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            sendRequestToGetPopular()
        }

        viewModel.tvShowsDetails.observe(viewLifecycleOwner) {
            refreshAdapter(it.movies!!)
        }
    }
}