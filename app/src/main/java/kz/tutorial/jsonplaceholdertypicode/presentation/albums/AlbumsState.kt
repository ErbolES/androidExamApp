package kz.tutorial.jsonplaceholdertypicode.presentation.albums

import kz.tutorial.jsonplaceholdertypicode.domain.entity.Album

sealed class AlbumsState {
    data class Success(val listAlbums:List<Album>): AlbumsState()
    data class Error(val error: String): AlbumsState()
    object Loading: AlbumsState()
}