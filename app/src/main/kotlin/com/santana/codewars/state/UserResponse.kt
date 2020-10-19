package com.santana.codewars.state

sealed class UserResponse<T>() {
    class UserLoading<T>: UserResponse<T>()
    class UserSuccess<T>(val data: T): UserResponse<T>()
    class UserNotFound<T>(val username: String): UserResponse<T>()
    class NetworkError<T>(val error: Throwable): UserResponse<T>()
    class GenericError<T>(val error: Throwable): UserResponse<T>()
}