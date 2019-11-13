package ua.willeco.clicon.utility

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.net.wifi.WifiManager
import android.net.wifi.WifiInfo
import ua.willeco.clicon.enums.ConnectionType

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

    private fun isLocalConnecting(context: Context): ConnectionType{
        var typeConnection = ConnectionType.NOT_CONNECTION
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    typeConnection = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->{
                            val mg = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                            val info:WifiInfo = mg.connectionInfo
                            //TODO insert correct name SSID (need to accept for what need check)
                            if (info.ssid.contains("x0r-test")){
                                ConnectionType.WIFI_WPECO
                            }else{
                                ConnectionType.WIFI
                            }
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLURAL
                        else -> ConnectionType.NOT_CONNECTION
                    }
                }
            }
        } else {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        val info = wifiManager?.connectionInfo
                        if (info?.ssid?.contains("x0r-test")!!){
                            ConnectionType.WIFI_WPECO
                        }else{
                            ConnectionType.WIFI
                        }
                    }else{
                        if (type == ConnectivityManager.TYPE_MOBILE) {
                            ConnectionType.CELLURAL
                        }else{
                            ConnectionType.NOT_CONNECTION
                        }
                    }
                }
            }
        }

        return typeConnection
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isLocalConnected: ConnectionType)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}