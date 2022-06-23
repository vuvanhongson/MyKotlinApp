package com.example.mykotlinapp.util.di

import com.example.mykotlinapp.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { HomeViewModel(userRepository = get()) }

}