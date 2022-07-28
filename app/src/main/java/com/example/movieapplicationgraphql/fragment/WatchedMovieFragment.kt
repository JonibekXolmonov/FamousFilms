package com.example.movieapplicationgraphql.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapplicationgraphql.PopularMoviesQuery
import com.example.movieapplicationgraphql.R
import com.example.movieapplicationgraphql.adapter.TVShowAdapter
import com.example.movieapplicationgraphql.databinding.FragmentPopularMoviesBinding
import com.example.movieapplicationgraphql.databinding.FragmentWatchedMovieBinding
import com.example.movieapplicationgraphql.model.TVShow
import com.example.movieapplicationgraphql.utils.Visibility.hide
import com.example.movieapplicationgraphql.utils.Visibility.show
import com.example.movieapplicationgraphql.viewmodel.HomeViewModel
import com.example.movieapplicationgraphql.viewmodel.WatchedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchedMovieFragment : Fragment(R.layout.fragment_watched_movie) {

    private lateinit var binding: FragmentWatchedMovieBinding
    private val viewModel: WatchedViewModel by viewModels()
    private lateinit var adapter: TVShowAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWatchedMovieBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        viewModel.dbGetWatchedMovies()

        refreshAdapter(listOf())

        initObservers()
    }

    private fun refreshAdapter(movies: List<PopularMoviesQuery.Movie>) {
        adapter = TVShowAdapter(movies)
        binding.rvWatchedMovies.adapter = adapter
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
            viewModel.dbGetWatchedMovies()
        }

        viewModel.tvShowsDetailsDB.observe(viewLifecycleOwner) {
            refreshAdapter(cast(it))
        }
    }

    private fun cast(movies: List<TVShow>?): List<PopularMoviesQuery.Movie> {
        return ArrayList<PopularMoviesQuery.Movie>().apply {
            movies!!.forEach {
                this.add(
                    PopularMoviesQuery.Movie(
                        it.id,
                        it.original_title,
                        it.release_date,
                        it.poster_path
                    )
                )
            }
        }
    }
}
