package com.echwood.androidcleanarch.networking;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED


/**
 * @author  UkB.
 * @version v1
 */


public class NetworkError(e: Throwable): Throwable() {
    private val DEFAULT_ERROR_MESSAGE: String = "Something went wrong! Please try again.";
    public val NETWORK_ERROR_MESSAGE: String = "No Internet Connection!";
    private val  ERROR_MESSAGE_HEADER: String = "Error-Message";
    private lateinit var  error: Throwable;

     init {
         error = e
     }


    fun isAuthFailure(): Boolean {
        return error is HttpException &&
                (error as HttpException).code() == HTTP_UNAUTHORIZED;
    }

    fun isResponseNull(): Boolean {
        return error is HttpException && (error as HttpException).response() == null;
    }

    fun getAppErrorMessage(): String {
        if (this.error is IOException) return NETWORK_ERROR_MESSAGE;
        if (!(this.error is HttpException)) return DEFAULT_ERROR_MESSAGE;
        val response = (this.error as HttpException).response();
        if (response != null) {
            val status: String = getJsonStringFromResponse(response as retrofit2.Response<Any>);
            if (!TextUtils.isEmpty(status)) return status;

            val headers = response.headers().toMultimap();
            if (headers.containsKey(ERROR_MESSAGE_HEADER))
                return headers.get(ERROR_MESSAGE_HEADER)!!.get(0);
        }

        return DEFAULT_ERROR_MESSAGE;
    }

    protected fun getJsonStringFromResponse(response: retrofit2.Response<Any>): String {
        try {
            val jsonString = response.errorBody()!!.string();
            val errorResponse =  Gson().fromJson(jsonString, Response::class.java);
            return errorResponse.status;
        } catch (e: Exception) {
            return null!!;
        }
    }

    fun getError(): Throwable {
        return error;
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is NetworkError) return true;
        if (obj == null || obj !is NetworkError) return false;

        val netErr: NetworkError = obj as NetworkError;

        return if (error !=null)  error.equals(netErr.error) else netErr.error == null;

    }

    override fun hashCode(): Int {
        return if (error != null) error.hashCode() else 0;
    }
}