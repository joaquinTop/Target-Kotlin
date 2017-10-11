package com.joaquin.toptierlabs.targetmvd.business

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
                ?.newBuilder()
                ?.addHeader("Content-Type", "application/json")
                ?.addHeader("Accept", "application/json")
                ?.build()

        return chain!!.proceed(request)
    }
}
