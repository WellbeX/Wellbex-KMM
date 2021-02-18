package com.wellbex.shared

import kotlin.reflect.KSuspendFunction1
/**
 * @see [https://proandroiddev.com/railway-oriented-programming-in-kotlin-f1bceed399e5]
 */
sealed class Result<T>
data class Success<T>(val value: T) : Result<T>()
data class Failure<T>(val errorMessage: String) : Result<T>()

fun <T> success(value: T) = Success(value)
fun <T> fail(errorMessage: String) = Failure<T>(errorMessage)

suspend infix fun <T, U> Result<T>.then(f: KSuspendFunction1<T, Result<U>>) =
    when (this) {
        is Success -> f(this.value)
        is Failure -> Failure(this.errorMessage)
    }


infix fun <T, U> Result<T>.then(f: (T) -> Result<U>) =
    when (this) {
        is Success -> f(this.value)
        is Failure -> Failure(this.errorMessage)
    }

infix fun <T> Result<T>.otherwise(f: (String) -> Unit) =
    if (this is Failure) f(this.errorMessage) else Unit

suspend  infix fun <T> Result<T>.otherwise(f: KSuspendFunction1<String, Unit>) =
    if (this is Failure) f(this.errorMessage) else Unit