package com.davidmendozamartinez.gangame.sdk

import okhttp3.*

class MockResponseInterceptor(
    private val responses: HashMap<String, String>,
    private val defaultJsonResponse: String
) : Interceptor {

    private val mediaTypeJson = MediaType.parse("application/json")

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = getResponseFor(request.url().toString())

        return Response.Builder()
            .body(ResponseBody.create(mediaTypeJson, response))
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