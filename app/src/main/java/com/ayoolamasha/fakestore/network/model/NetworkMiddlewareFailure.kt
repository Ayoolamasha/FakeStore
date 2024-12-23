package com.ayoolamasha.fakestore.network.model


class NetworkMiddlewareFailure(
    val middleWareExceptionMessage: String,
) : Failure.CustomFailure()

object TimeOut : Failure.CustomFailure()

object NetworkConnectionLostSuddenly : Failure.CustomFailure()

object SSLError : Failure.CustomFailure()

/**
 * If your service return some custom error use this with the given attars you expect.
 */
data class ServiceBodyFailure(
    val internalCode: Int,
    val internalMessage: String?
) : Failure.CustomFailure()


data class ResponseMessage(val message: String?, val code: Int)