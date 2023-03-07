package com.github.johnondrej.scratchcard.shared.core.networking.di

import com.github.johnondrej.scratchcard.shared.core.networking.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}

private const val BASE_URL = "https://api.o2.sk/"
