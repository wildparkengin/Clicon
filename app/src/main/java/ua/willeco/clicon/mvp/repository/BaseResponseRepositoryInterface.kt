package ua.willeco.clicon.mvp.repository

import ua.willeco.clicon.enums.AppRequestEventType

interface BaseResponseRepositoryInterface {
    interface OnFinishedRequest {
        fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType)
        fun onFailureRequest(t: String)
    }
}