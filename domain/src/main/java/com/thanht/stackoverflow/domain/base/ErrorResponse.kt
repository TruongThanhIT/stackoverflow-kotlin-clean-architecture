package com.thanht.stackoverflow.domain.base

abstract class ErrorResponse : Throwable() {
    abstract val errorCode: String?
    abstract val errorMessage: String?
}
