package com.example.studentbeans

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentbeans.databinding.ActivityMainBinding
import com.example.studentbeans.viewmodel.LoginViewModel
import com.example.studentbeans.viewmodel.PostsViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val postsViewModel : PostsViewModel by inject()
    val loginViewModel : LoginViewModel by inject()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}