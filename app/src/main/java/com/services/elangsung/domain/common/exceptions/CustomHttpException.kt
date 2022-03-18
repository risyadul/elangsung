package com.services.elangsung.domain.common.exceptions

import com.services.elangsung.data.common.entity.ErrorResponse

open class CustomHttpException(private val response: ErrorResponse, message: String? = null) :
    Exception(message) {

    override val message: String?
        get() = response.message

    class BadRequestHttpException(response: ErrorResponse) :
        CustomHttpException(response, "Bad Request")

    class UnauthorizedHttpException(response: ErrorResponse) :
        CustomHttpException(response, "Unauthorized")

    class ForbiddenHttpException(response: ErrorResponse) :
        CustomHttpException(response, "Forbidden")

    class UnprocessableEntityHttpException(response: ErrorResponse) :
        CustomHttpException(response, "Unprocessable Entity")

    class InternalServerErrorHttpException(response: ErrorResponse) :
        CustomHttpException(response, "Internal Server Error")

    class NotFoundErrorHttpException(response: ErrorResponse) :
        CustomHttpException(response, "Not Found")
}
