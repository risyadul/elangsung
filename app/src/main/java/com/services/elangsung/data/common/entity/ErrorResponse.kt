package com.services.elangsung.data.common.entity

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName(SERIALIZED_NAME_MESSAGE) val message: String? = null
) {

    companion object {
        private const val SERIALIZED_NAME_MESSAGE = "message"
    }
}
