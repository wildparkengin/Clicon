package ua.willeco.clicon.mvp.repository

interface BaseResponseRepositoryInterface {
    interface OnFinishedRequest {
        fun onFinishedRequest(responseData: Any)
        fun onFailureRequest(t: String)
    }
}