package ru.hse.buildingapp.network.authmodels

sealed interface AuthRespState {
    object Success : AuthRespState
    object Loading : AuthRespState
    object InvalidCredentials : AuthRespState
    data class UnknownServerError(val code : Int) : AuthRespState
    object ConnectionError : AuthRespState
}

sealed interface RespState<T> {

    data class Success<A>(val res : A) : RespState<A>
    class Loading<A> : RespState<A>
    data class UnknownError<A>(val code : Int) : RespState<A>
    class ConnectionError<A> : RespState<A>
    class Unauthorized<A> : RespState<A>
}