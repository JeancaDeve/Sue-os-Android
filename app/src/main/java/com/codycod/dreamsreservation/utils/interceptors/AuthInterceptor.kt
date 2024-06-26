package com.codycod.dreamsreservation.utils.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor
    (private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()

        val requestBuilder = origin.newBuilder()
            .header("Authorization", "Bearer $token")

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}