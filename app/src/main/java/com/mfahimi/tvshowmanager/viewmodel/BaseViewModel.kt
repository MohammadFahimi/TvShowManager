package com.mfahimi.tvshowmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfahimi.domain.result.Result

open class BaseViewModel : ViewModel() {
    fun <T> mutableLiveData() = MutableLiveData<Result<T>>()
    fun <T> MutableLiveData<Result<T>>.emit(it: Result<T>) {
        postValue(it)
    }

    fun <T> MutableLiveData<Result<T>>.immutable(): LiveData<Result<T>> = this
}
