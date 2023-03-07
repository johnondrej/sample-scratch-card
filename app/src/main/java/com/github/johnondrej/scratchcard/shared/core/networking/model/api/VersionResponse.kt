package com.github.johnondrej.scratchcard.shared.core.networking.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VersionResponse(
    val android: String?
)
