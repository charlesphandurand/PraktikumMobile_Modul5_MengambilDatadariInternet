package com.example.animes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animes.network.Anime
import com.example.animes.network.AnimeApi
import kotlinx.coroutines.launch

enum class AnimeApiStatus {LOADING, ERROR, DONE}

class AnimeViewModel : ViewModel() {

    private val _status = MutableLiveData<AnimeApiStatus>()
    val status : LiveData<AnimeApiStatus> = _status

    private val _animes = MutableLiveData<List<Anime>>()
    val animes : LiveData<List<Anime>> = _animes

    private val _anime = MutableLiveData<Anime>()
    val anime : LiveData<Anime> = _anime

    fun getAnimeList(){
        _status.value = AnimeApiStatus.LOADING
        viewModelScope.launch {
            try {
                _animes.value = AnimeApi.retrofitService.getAnimes()
                _status.value = AnimeApiStatus.DONE
            }catch (e: Exception){
                e.printStackTrace()
                _status.value = AnimeApiStatus.ERROR
            }
        }

    }

    fun onAnimeClicked(anime: Anime) {
        _anime.value = anime
    }
}
