package com.example.socialapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialapp.data.repo.AuthRepository
import com.example.socialapp.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
   val isLoggedIn: StateFlow<Boolean?> =_isLoggedIn

    init{
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            val id =sessionManager.userIdFlow.firstOrNull()
            _isLoggedIn.value = id !=null
        }
    }


}