package com.ayoolamasha.fakestore.network.middleware

import com.ayoolamasha.fakestore.network.model.NetworkMiddlewareFailure


abstract class NetworkMiddleware {
    abstract val failure: NetworkMiddlewareFailure

    abstract fun isValid(): Boolean
}