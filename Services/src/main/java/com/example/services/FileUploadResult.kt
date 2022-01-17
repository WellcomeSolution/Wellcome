package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class FileUploadResult(
    @SerializedName("FileName")
    var fileName: String
)