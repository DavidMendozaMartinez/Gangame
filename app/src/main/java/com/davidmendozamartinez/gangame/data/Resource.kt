package com.davidmendozamartinez.gangame.data

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> =
            Resource(
                Status.SUCCESS,
                data,
                null
            )

        fun <T> error(message: String?): Resource<T> =
            Resource(
                Status.ERROR,
                null,
                message
            )
    }
}