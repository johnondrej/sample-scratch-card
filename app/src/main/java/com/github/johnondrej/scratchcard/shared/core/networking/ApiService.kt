package com.github.johnondrej.scratchcard.shared.core.networking

import com.github.johnondrej.scratchcard.shared.core.networking.model.api.VersionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("version")
    suspend fun getVersion(@Query("code") code: Int): VersionResponse
}
