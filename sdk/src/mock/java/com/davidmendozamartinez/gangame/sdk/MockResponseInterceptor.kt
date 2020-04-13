package com.davidmendozamartinez.gangame.sdk

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockResponseInterceptor(
    private val responses: HashMap<String, String>,
    private val defaultJsonResponse: String
) : Interceptor {

    private val mediaTypeJson = "application/json".toMediaType()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = getResponseFor(request.url.toString())

        return Response.Builder()
            .body(response.toResponseBody(mediaTypeJson))
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("success")
            .build()
    }

    private fun getResponseFor(url: String): String {
        return if (responses.containsKey(url))
            responses[url]!!
        else
            defaultJsonResponse
    }
}