package com.example.authapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.data.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository): ViewModel(){
    var loginResult: ((Boolean) -> Unit)? = null
    var registerResult: ((Boolean) -> Unit)? = null

    fun register(
        email: String,
        password: String,
        name: String,
        onResult: (Boolean) -> Unit,
    ){
        viewModelScope.launch{
            val sucess = repository.registerUser(email, password, name)
            onResult(sucess)
        }
    }

    fun login(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val sucess = repository.loginUser(email, password)
            onResult(sucess)
        }
    }

    fun resetPassword(
        email: String,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val sucess = repository.resetPassword(email)
            onResult(sucess)
        }
    }

    fun getUserName(
        onResult: (String?) -> Unit
    ){
        viewModelScope.launch {
            val name = repository.getUserName()
            onResult(name)
        }
    }

    fun logInWIthGoogle(
        idToken: String,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val sucess = repository.loginWithGoogle(idToken)
            onResult(sucess)
        }
    }

    fun getGoogleClient(
        context: Context,
    ): GoogleSignInClient{
        return repository.getGoogleSignInClient(context)
    }

    fun logOut(){
        repository.logOut()
    }
}