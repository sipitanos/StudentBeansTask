package com.example.studentbeans.viewmodel

import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private var email: String = ""
    private var password: String = ""

    fun setEmail(email: String){
        this.email = email
    }
    fun getEmail(): String{
        return this.email
    }

    fun setPassword(password: String){
        this.password = password
    }

    fun getPassword(): String{
        return this.password
    }
}