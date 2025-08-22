package com.example.socialapp.presentation.signup



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialapp.domain.usecase.SignUpUseCase
import com.example.socialapp.data.repo.AuthError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    fun onUsernameChange(newUsername: String) {
        _state.value = _state.value.copy(username = newUsername)
    }

    fun onEmailChange(newEmail: String) {
        _state.value = _state.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPass: String) {
        _state.value = _state.value.copy(password = newPass)
    }

    fun signUp(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                signUpUseCase(
                    _state.value.username,
                    _state.value.email,
                    _state.value.password
                )
                onSuccess()
            } catch (e: AuthError.EmailAlreadyUsed) {
                _state.value = _state.value.copy(error = "Email is already registered")
            }
            // bnst5dm finally b3d try and catch 34an alcode da yt3ml fe kol al7lat
            finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}
