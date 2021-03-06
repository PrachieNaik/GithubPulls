package com.example.githubpulls.data

/** A Result class providing status, data and exception(if any) of API result*/
data class Result<out T>(
    val status: Status,
    val data: T?,
    val throwable: Throwable?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable?): Result<T> {
            return Result(Status.ERROR, null, throwable)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}