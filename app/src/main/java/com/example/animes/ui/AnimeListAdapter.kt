package com.example.animes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animes.databinding.ListViewItemBinding
import com.example.animes.network.Anime

class AnimeListAdapter(val clickListener: AnimeListener) :
    ListAdapter<Anime, AnimeListAdapter.AnimeViewHolder>(DiffCallback) {

    class AnimeViewHolder(
        var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: AnimeListener, anime: Anime) {
            binding.anime = anime
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Anime>() {

        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.original_title_romanised == newItem.original_title_romanised && oldItem.original_title == newItem.original_title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(clickListener, anime)
    }
}

class AnimeListener(val clickListener: (anime: Anime) -> Unit) {
    fun onClick(anime: Anime) = clickListener(anime)
}
