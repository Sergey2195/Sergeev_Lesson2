package com.lesson.sergeev_lesson2.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _passwordErrorStateFlow = MutableStateFlow(false)
    val passwordErrorStateFlow
        get() = _passwordErrorStateFlow.asStateFlow()
    private val _btnCanClickedStateFlow = MutableStateFlow(false)
    val btnCanClickedStateFlow
        get() = _btnCanClickedStateFlow.asStateFlow()
    private val _passwordIsVisibleStateFlow = MutableStateFlow(false)
    val passwordIsVisibleStateFlow
        get() = _passwordIsVisibleStateFlow.asStateFlow()

    fun checkPassword(password: String): Boolean {
        return if (password != RIGHT_PASSWORD) {
            _passwordErrorStateFlow.value = true
            false
        } else {
            true
        }
    }

    fun changePasswordVisibility() {
        _passwordIsVisibleStateFlow.value = !_passwordIsVisibleStateFlow.value
    }

    fun passwordEditTextChanged(password: String) {
        if (_passwordErrorStateFlow.value) {
            _passwordErrorStateFlow.value = false
        }
        _btnCanClickedStateFlow.value = password.isNotEmpty()

    }

    companion object {
        private const val RIGHT_PASSWORD = "12345"
    }
}