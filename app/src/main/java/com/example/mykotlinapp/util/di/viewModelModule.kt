package com.example.mykotlinapp.util.di

import com.example.mykotlinapp.features.book.BookNextPageViewModel
import com.example.mykotlinapp.features.complain.ComplainViewModel
import com.example.mykotlinapp.features.home.HomeViewModel
import com.example.mykotlinapp.features.map.MapViewModel
import com.example.mykotlinapp.features.search.InformationViewModel
import com.example.mykotlinapp.features.search.RequestUpdateViewModel
import com.example.mykotlinapp.features.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { HomeViewModel(userRepository = get()) }
    viewModel { SearchViewModel(userRepository = get()) }
    viewModel { MapViewModel(userRepository = get()) }
    viewModel { InformationViewModel() }
    viewModel { BookNextPageViewModel(userRepository = get()) }
    viewModel { ComplainViewModel(userRepository = get()) }
    viewModel { RequestUpdateViewModel(userRepository = get()) }

}