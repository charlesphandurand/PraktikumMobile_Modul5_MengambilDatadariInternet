package com.example.animes

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animes.network.Anime
import com.example.animes.ui.AnimeApiStatus
import com.example.animes.ui.AnimeListAdapter

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: AnimeApiStatus?) {
    when (status) {
        AnimeApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AnimeApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        AnimeApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Anime>?) {
    val adapter = recyclerView.adapter as AnimeListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, image: String?) {
    image?.let {
        val imgUri = image.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
