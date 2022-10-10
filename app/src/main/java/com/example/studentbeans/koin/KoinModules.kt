package com.example.studentbeans.koin

import com.example.studentbeans.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.studentbeans.viewmodel.PostsViewModel

val postsViewModel = module {
    viewModel { PostsViewModel() }
}

val loginViewModel = module{
    viewModel{ LoginViewModel()  }
}