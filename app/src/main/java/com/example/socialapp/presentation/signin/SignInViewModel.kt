package com.example.socialapp.presentation.signin



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialapp.domain.usecase.SignInUseCase
import com.example.socialapp.data.repo.AuthError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {

   //_state al view model y2dr y8erha mutable
    private val _state = MutableStateFlow(SignInState())
    //read only lel ui
    val state: StateFlow<SignInState> = _state

    fun onEmailChange(newEmail: String) {
        _state.value = _state.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPass: String) {
        _state.value = _state.value.copy(password = newPass)
    }

    fun signIn(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                signInUseCase(_state.value.email, _state.value.password)
                onSuccess()
            } catch (e: AuthError.InvalidCredentials) {
                _state.value = _state.value.copy(error = "Invalid email or password")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}
