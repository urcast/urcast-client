package com.example.urcast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urcast.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isRegistrationSuccessful: Boolean = false,
    val errorMessage: String? = null
)

class AuthViewModel(
    private val repository: AuthRepository = `AuthRepository`()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    private var accessToken: String? = null

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            try {
                val response = repository.login(email, password)

                accessToken = response.access_token

                _uiState.value = AuthUiState(
                    isLoggedIn = true
                )

            } catch (e: Exception) {
                _uiState.value = AuthUiState(
                    errorMessage = e.message ?: "Login failed"
                )
            }
        }
    }
    fun register(
        username: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            try {
                repository.register(username, email, password)

                _uiState.value = AuthUiState(
                    isRegistrationSuccessful = true
                )

            } catch (e: Exception) {
                _uiState.value = AuthUiState(
                    errorMessage = e.message ?: "Registration failed"
                )
            }
        }
    }
    fun resetState() {
        _uiState.value = AuthUiState()
    }
}