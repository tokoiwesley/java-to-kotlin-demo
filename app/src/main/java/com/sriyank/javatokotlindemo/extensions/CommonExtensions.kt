package com.sriyank.javatokotlindemo.app

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.sriyank.javatokotlindemo.models.ErrorResponse
import okhttp3.ResponseBody
import java.io.IOException

fun Context.showErrorMessage(errorBody: ResponseBody) {
    val gson = GsonBuilder().create()
    try {
        val errorResponse = gson.fromJson(errorBody.string(), ErrorResponse::class.java)
        toast(errorResponse.message!!)
    } catch (e: IOException) {
        Log.i("Exception ", e.toString())
    }
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
