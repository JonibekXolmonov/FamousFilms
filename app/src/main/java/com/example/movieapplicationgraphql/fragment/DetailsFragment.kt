package com.example.movieapplicationgraphql.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movieapplicationgraphql.R
import com.example.movieapplicationgraphql.databinding.FragmentDetailsBinding
import com.example.movieapplicationgraphql.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)

        binding.ivMovie.transitionName = arguments?.get("iv_movie").toString()

        initViews()
    }

    private fun initViews() {
        arguments?.let {
            binding.apply {
                tvName.text = it.get("show_name").toString()
                tvType.text = it.get("show_network").toString()
                Glide.with(this@DetailsFragment)
                    .load(Constant.START_URL + it.get("show_img").toString())
                    .into(ivMovie)
            }
        }
    }
}