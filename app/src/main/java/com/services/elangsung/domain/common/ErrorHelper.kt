package com.services.elangsung.domain.common

import com.google.gson.Gson
import com.services.elangsung.data.common.entity.ErrorResponse
import com.services.elangsung.domain.common.exceptions.CustomHttpException
import retrofit2.HttpException
import javax.inject.Inject

class ErrorHelper @Inject constructor(private val gson: Gson) {

    fun buildThrowable(throwable: Throwable): Throwable {
        return if (throwable is HttpException) {
            val body = throwable.response()?.errorBody()?.string()
            val errorResponse =
                body?.let {
                    gson.fromJson(it, ErrorResponse::class.java)
                } ?: ErrorResponse()
            when (throwable.code()) {
                HttpStatusCodes.BAD_REQUEST ->
                    CustomHttpException.BadRequestHttpException(errorResponse)
                HttpStatusCodes.UNAUTHORIZED ->
                    CustomHttpException.UnauthorizedHttpException(errorResponse)
                HttpStatusCodes.FORBIDDEN ->
                    CustomHttpException.ForbiddenHttpException(errorResponse)
                HttpStatusCodes.UNPROCESSABLE_ENTITY ->
                    CustomHttpException.UnprocessableEntityHttpException(errorResponse)
                HttpStatusCodes.INTERNAL_SERVER_ERROR ->
                    CustomHttpException.InternalServerErrorHttpException(errorResponse)
                HttpStatusCodes.NOT_FOUND ->
                    CustomHttpException.NotFoundErrorHttpException(errorResponse)
                else -> CustomHttpException(errorResponse)
            }
        } else throwable
    }
}
