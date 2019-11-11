package ua.willeco.clicon.utility

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.net.wifi.WifiManager
import android.net.NetworkInfo
import android.net.wifi.WifiInfo

/**
 * Receiver to check connection extended BroadcastReceiver
 */

class ReceiverConnectionChanged : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(isLocalConnecting(context!!))
        }
    }

    /**
     * Method that check connection state mode
     *
     * @param context Interface to global information about an application environment
     * @return Boolean type: true - isn`t connect to raspberry PI or false - to global connection result
     */

    private fun isLocalConnecting(context: Context): Boolean {
        var isLocalNetwork = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    isLocalNetwork = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->{
                            val mg = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                            val info:WifiInfo = mg.connectionInfo
                            //TODO insert correct name SSID
                            info.ssid == "x0r-test"
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> false
                        else -> false
                    }
                }
            }
        } else {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        val info = wifiManager?.connectionInfo
                        isLocalNetwork = info?.ssid == "x0r-test"
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        isLocalNetwork = false
                    }
                }
            }
        }

        return isLocalNetwork
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isLocalConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}