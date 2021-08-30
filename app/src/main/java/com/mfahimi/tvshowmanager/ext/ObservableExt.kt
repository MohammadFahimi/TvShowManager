package com.mfahimi.tvshowmanager.ext


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.mfahimi.domain.result.Result

inline fun <T> LiveData<Result<T>>.handleApiResponse(
    observer: LifecycleOwner,
    crossinline onLoad: () -> Unit,
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: ((error: Throwable) -> Unit)
) {
    this.observe(observer, {
        when (it) {
            is Result.Loading -> onLoad.invoke()
            is Result.Success -> onSuccess(it.data)
            is Result.Error -> onError(it.exception)
        }
    })
}
