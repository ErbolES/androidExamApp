package kz.tutorial.jsonplaceholdertypicode.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.tutorial.jsonplaceholdertypicode.data.use_case.GetUserUseCaseImpl
import kz.tutorial.jsonplaceholdertypicode.domain.entity.User

class UsersViewModel(private val getUsersUseCase: GetUserUseCaseImpl) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            val result = getUsersUseCase.invoke(userId)
            _user.value = result
        }
    }
}
