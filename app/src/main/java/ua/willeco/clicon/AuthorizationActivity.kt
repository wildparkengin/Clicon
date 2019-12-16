package ua.willeco.clicon

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_authorization.*
import ua.willeco.clicon.enums.ConnectionType
import ua.willeco.clicon.enums.EventType
import ua.willeco.clicon.event_bus.RxBus
import ua.willeco.clicon.event_bus.RxEvent
import ua.willeco.clicon.http.ResponcesApi
import ua.willeco.clicon.http.RetrofitClient
import ua.willeco.clicon.utility.BiometricalAuth
import ua.willeco.clicon.utility.ReceiverConnectionChanged
import ua.willeco.clicon.utility.Validation


class AuthorizationActivity : AppCompatActivity(),
    ReceiverConnectionChanged.ConnectivityReceiverListener {


    private val PERMISSION_REQUEST_CODE = 123
    private var doubleBackPressed:Boolean = false
    private val COMPLETED_ONBOARDING_PREF_NAME:String = "isNeedHelp"
    private var connectionBroadcastReceiver: BroadcastReceiver? = null
    private lateinit var rxBusDisposable: Disposable
    private var connectionType = ConnectionType.NOT_CONNECTION

    //TODO create permition to check WIFI network
    override fun onNetworkConnectionChanged(isLocalConnected: ConnectionType) {
        val iconTypeConnection = findViewById<ImageView>(R.id.img_type_connection)

        connectionType = isLocalConnected

        when(isLocalConnected){
            ConnectionType.NOT_CONNECTION ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_unknow_connection_mode))}
            ConnectionType.WIFI_WPECO ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_local_mode_connection))
            }
            ConnectionType.CELLURAL ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_server_mode_connection))}

            else ->{iconTypeConnection.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.ic_server_mode_connection))}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        btn_submit_auth.setOnClickListener { submitLogin() }

        img_biometric_pass.setOnClickListener {
            BiometricalAuth.initBiometrical(this)
        }

        connectionBroadcastReceiver = ReceiverConnectionChanged()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (Validation.isLocationPermissionAccess(this)){
                registerReceiver(connectionBroadcastReceiver,IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
            }else{
                val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                ActivityCompat.requestPermissions(this,permissions,PERMISSION_REQUEST_CODE)
            }
        }else{
            registerReceiver(connectionBroadcastReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }

        initRxBusEvent()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {

        when(requestCode){
            PERMISSION_REQUEST_CODE ->{
                if (grantResults.isNotEmpty()){
                    if ( grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                        registerReceiver(connectionBroadcastReceiver,IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
                    }else{
                        Toast.makeText(this, "grantResults is empty", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun initRxBusEvent(){
        rxBusDisposable = RxBus.listen(RxEvent.EventChanges::class.java).subscribe{
            if (it.changesEvent == EventType.AUTENTIFICATE){
                openNextActivity(false)
            }
        }

        //val cf = Validation.bcRypt()
        //println(cf)
    }

    private fun submitLogin(){
        val emailCheck: Boolean
        val passwordCheck: Boolean

        if (Validation.isEmptyFieldEntered(edt_email_auth)){
            Validation.setErrorToEditText(edt_email_auth,"empty field")
            return
        }else{
//            if (!Validation.isEmptyFieldEntered(edt_email_auth)){
//                Validation.setErrorToEditText(edt_email_auth,"wrong email")
//                return
//            }else{
//
//            }
            emailCheck = true
        }

        if (Validation.isEmptyFieldEntered(edt_password_auth)){
            Validation.setErrorToEditText(edt_password_auth,"empty field")
            return
        }else{
            if (!Validation.isValidPasswordLenth(edt_password_auth.text.toString())){
                Validation.setErrorToEditText(edt_password_auth,"to short password")
                return
            }else{
                passwordCheck = true
            }
        }

        if (emailCheck && passwordCheck){
            getAuthorizationRequest()
        }
    }


    fun getAuthorizationRequest(){
        val login = edt_email_auth.text.trim().toString()
        val passw = edt_password_auth.text.trim().toString()

        if (!Validation.isEmptyFieldEntered(edt_email_auth)){
            if (Validation.isValidPasswordLenth(passw)){
                val service = RetrofitClient.RetrofitFactory.makeRetrofitService(connectionType)
                ResponcesApi.authentificateRequest(this,service,
                    login,passw)
            }
            //showOnBoardingFragmentToUser()
        }
    }

    override fun onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed()
            return
        }

        this.doubleBackPressed = true

        Toast.makeText(this, getString(R.string.double_back_pressed), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackPressed = false }, 2000)
    }

    private fun showOnBoardingFragmentToUser(){

        PreferenceManager.getDefaultSharedPreferences(this).apply {
            openNextActivity(getBoolean(COMPLETED_ONBOARDING_PREF_NAME, true))
        }
    }

    private fun openNextActivity(isNeedHelp:Boolean){

        val intent:Intent = if (isNeedHelp){
            Intent(this, MainActivity::class.java)
        }else{
            Intent(this, MainActivity::class.java)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        unregisterReceiver(connectionBroadcastReceiver)
        rxBusDisposable.dispose()

        startActivity(intent)
    }

    override fun onResume() {
        ReceiverConnectionChanged.connectivityReceiverListener = this
        super.onResume()
    }
}
