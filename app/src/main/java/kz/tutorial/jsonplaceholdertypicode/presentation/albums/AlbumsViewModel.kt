package kz.tutorial.jsonplaceholdertypicode.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.tutorial.jsonplaceholdertypicode.data.use_case.GetAlbumsUseCaseImpl

class AlbumsViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCaseImpl
) : ViewModel() {

    private val _albumsLiveData = MutableLiveData<AlbumsState>()
    val albumsLiveData: LiveData<AlbumsState> = _albumsLiveData

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        _albumsLiveData.value = AlbumsState.Loading
        viewModelScope.launch {
            try {
                val albums = getAlbumsUseCase()
                _albumsLiveData.value = AlbumsState.Success(albums)
            } catch (e: Exception) {
                _albumsLiveData.value = AlbumsState.Error(e.message ?: "Unknown error")
            }
        }
    }
}