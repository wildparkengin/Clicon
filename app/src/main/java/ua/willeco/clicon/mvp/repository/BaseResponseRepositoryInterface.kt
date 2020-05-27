package ua.willeco.clicon.mvp.repository

interface BaseResponseRepositoryInterface {
    interface OnFinishedRequest {
        fun onFinishedRequest(response: Any)
        fun onFailureRequest(t: Throwable?)
    }
}