package com.github.johnondrej.scratchcard.features.status.di

import com.github.johnondrej.scratchcard.features.status.presentation.StatusScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val statusModule = module {

    viewModelOf(::StatusScreenViewModel)
}
