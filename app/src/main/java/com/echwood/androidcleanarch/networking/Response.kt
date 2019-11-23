package com.echwood.androidcleanarch.networking;

import com.google.gson.annotations.SerializedName;
/**
 * @author  UkB.
 * @version v1
 */
data class Response (
    @SerializedName("status")
    val status: String
)
