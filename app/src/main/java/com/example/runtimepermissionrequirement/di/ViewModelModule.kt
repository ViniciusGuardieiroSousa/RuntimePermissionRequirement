package com.example.runtimepermissionrequirement.di

import com.example.runtimepermissionrequirement.MainViewModel
import org.koin.dsl.module

val viewModel = module{
    factory { MainViewModel() }
}