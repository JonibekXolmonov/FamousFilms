package com.example.movieapplicationgraphql.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplicationgraphql.utils.Constant
import com.example.movieapplicationgraphql.PopularMoviesQuery
import com.example.movieapplicationgraphql.databinding.ItemTvShowBinding

class TVShowAdapter(
    private val items: List<PopularMoviesQuery.Movie>?
) :
    RecyclerView.Adapter<TVShowAdapter.VH>() {


    var onClick: ((PopularMoviesQuery.Movie, ImageView) -> Unit)? = null

    class VH(val view: ItemTvShowBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemTvShowBinding.inflate(
            LayoutInflater.from(parent.context)
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        val tvShow = items!![position]
        holder.view.apply {
            Glide.with(ivMovie.context).load(Constant.START_URL + tvShow.poster_path).into(ivMovie)
            tvName.text = tvShow.original_title
            tvType.text = tvShow.release_date

            ivMovie.setOnClickListener {
                ivMovie.transitionName = tvShow.original_title
                try {
                    onClick!!.invoke(tvShow, ivMovie)
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun getItemCount(): Int = items!!.size
}