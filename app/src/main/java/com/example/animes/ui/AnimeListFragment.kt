package com.example.animes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.animes.R
import com.example.animes.databinding.FragmentAnimeListBinding

class AnimeListFragment : Fragment() {

    private val viewModel: AnimeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAnimeListBinding.inflate(inflater)
        viewModel.getAnimeList()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = AnimeListAdapter(AnimeListener { anime ->
            viewModel.onAnimeClicked(anime)
            findNavController()
                .navigate(R.id.action_animeListFragment_to_animeDetailFragment)
        })

        return binding.root
    }
}
