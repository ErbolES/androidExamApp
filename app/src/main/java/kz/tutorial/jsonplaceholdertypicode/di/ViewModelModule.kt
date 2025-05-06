package kz.tutorial.jsonplaceholdertypicode.di

import kz.tutorial.jsonplaceholdertypicode.presentation.albums.AlbumsViewModel
import kz.tutorial.jsonplaceholdertypicode.presentation.post_details.PostDetailsViewModel
import kz.tutorial.jsonplaceholdertypicode.presentation.posts.PostsViewModel
import kz.tutorial.jsonplaceholdertypicode.presentation.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        PostsViewModel(get())
    }
    viewModel { (postId: Int) ->
        PostDetailsViewModel(
            getPostPreviewCommentsUseCase = get(),
            getPostUseCase = get(),
            getUserUseCase = get(),
            postId = postId
        )
    }
    viewModel {
        AlbumsViewModel(get())
    }
    viewModel { UsersViewModel(get()) }
}