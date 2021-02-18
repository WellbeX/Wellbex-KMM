package com.wellbex.shared

sealed class ViewStateResult {
    object Loading : ViewStateResult()
    object Success : ViewStateResult()
    object IDLE : ViewStateResult()
    data class Failed(val errorType: Throwable) : ViewStateResult()
}

sealed class ViewStateData {
    object Loading : ViewStateData()
    object IDLE : ViewStateData()
    object Success : ViewStateData()
    data class SuccessData<T>(val data: T) : ViewStateData()
    data class Failed(val errorType: Throwable) : ViewStateData()
}