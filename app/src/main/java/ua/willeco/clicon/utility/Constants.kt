package ua.willeco.clicon.utility

import android.Manifest

object Constants {
    const val REQUEST_CODE_CAMERA_PERMISSIONS = 10

    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    /**
     * requests url connecting to server
     */
    const val BASE_URL = "https://80.252.241.65:90/server/"
    /**
     * requests url connecting to local device
     */
    const val AP_RSB = "http://192.168.42.1:8080/cqa_http/"
    /**
     * Time to display splash view in ms
     */
    const val SPLASH_DISPLAY_TIME = 2000
    /**
     * name user shared preferences
     */
    const val PROPERTY_SHARED_PREFERENCES = "clicon_private"
    /**
     * Time to okHttpClient read timeout in sec
     */
    const val HTTP_READ_TIMEOUT= 3L
    /**
     * Time to okHttpClient connect timeout in sec
     */
    const val HTTP_CONNECT_TIMEOUT = 3L
    /**
     * Time to okHttpClient connect timeout in sec
     */
    const val PREFERENCES_LOGIN_HASH = "login_data"

    /**
     * Time to okHttpClient connect timeout in sec
     */
    const val RESULT_FROM_DIALOG = 0

    const val DIALOG_DELETE_ARGUMENTS = "dialog_arg_delete"
    const val DIALOG_FACILITY_ARGUMENTS = "dialog_arg_facility"
    const val DIALOG_FACILITY_LIST_DEVICE_ARGUMENTS = "dialog_arg_facility_list"

}