package com.karevsky.napoleonit.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithErrorHandler(
    block: suspend () -> Unit,
    onError: (Throwable) -> Unit = { _ -> }
){
    launch(CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
        Log.e("Error", throwable.message, throwable)
    }) {
        block()
    }
}