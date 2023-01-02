package com.dahlaran.movshow.data

import android.accounts.NetworkErrorException
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.dahlaran.movshow.R
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import kotlin.reflect.KFunction

data class Error(val status: Int, val function: KFunction<*>? = null) {



    companion object {
        const val CODE_NETWORK_PROBLEM = 10
        const val CODE_HTTP_EXCEPTION = 11

        const val CODE_SUCCESS = 200
        const val CODE_NO_CONTENT = 204
        const val CODE_BAD_REQUEST = 400
        const val CODE_UNAUTHORIZED = 401
        const val CODE_PAYMENT_REQUIRED = 402
        const val CODE_FORBIDDEN = 403
        const val CODE_NOT_FOUND = 404
        const val CODE_TIMEOUT = 408


        fun fromString(errorMessage: String): Error {
            return try {
                Error(errorMessage.toInt())
            } catch (e: NumberFormatException) {
                Error(-1)
            }
        }
        fun fromException(exception: Exception): Error {
            return when (exception){
                is NetworkErrorException, is IOException -> Error(CODE_NETWORK_PROBLEM)
                is HttpException -> Error(CODE_HTTP_EXCEPTION)
                else ->  Error(-1)
            }
        }
    }

    fun showError(context: Context?,) {
        if (context != null) {
            if (!showUsingFunction(context)){
                showUsingFunction(context)
            }
        } else {
            Timber.e("No context to show error")
        }
    }
    private fun showUsingFunction(context: Context): Boolean{
        return false
    }

    private fun showUsingCodeOnly(context: Context){
        @StringRes val resId = when (status) {
            CODE_NETWORK_PROBLEM -> R.string.error_no_network
            CODE_HTTP_EXCEPTION -> R.string.error_http_exception
            CODE_SUCCESS -> R.string.error_success
            CODE_NO_CONTENT -> R.string.error_no_content
            CODE_BAD_REQUEST -> R.string.error_bad_request
            CODE_UNAUTHORIZED -> R.string.error_unauthorized
            CODE_PAYMENT_REQUIRED -> R.string.error_payment_required
            CODE_FORBIDDEN -> R.string.error_forbidden
            CODE_NOT_FOUND -> R.string.error_no_found
            CODE_TIMEOUT -> R.string.error_time_out
            else -> R.string.error_default
        }

        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }
}