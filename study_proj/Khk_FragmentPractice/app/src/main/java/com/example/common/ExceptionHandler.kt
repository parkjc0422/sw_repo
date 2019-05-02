package com.example.common

import android.content.Context
import android.widget.Toast

fun showExceptionByToast(appContext: Context, ex : Exception){
    ex.printStackTrace()
    Toast.makeText(appContext, ex.message, Toast.LENGTH_LONG)
}