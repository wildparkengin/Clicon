package ua.willeco.clicon

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_authorization.*
import ua.willeco.clicon.enums.ConnectionType
import ua.willeco.clicon.utility.BiometricalAuth
import ua.willeco.clicon.utility.ReceiverConnectionChanged
import ua.willeco.clicon.utility.Validation

class AuthorizationActivity : AppCompatActivity(),
    ReceiverConnectionChanged.ConnectivityReceiverListener {

    //TODO create permition to check WIFI network
    override fun onNetworkConnectionChanged(type: ConnectionType) {
        val iconTypeConnection = findViewById<ImageView>(R.id.img_type_connection)

        when(type){
            ConnectionType.NOT_CONNECTION ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_unknow_connection_mode))}
            ConnectionType.WIFI_WPECO ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_local_mode_connection))}
            ConnectionType.CELLURAL ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_server_mode_connection))}

            else ->{iconTypeConnection.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.ic_server_mode_connection))}
        }
    }

    private var doubleBackPressed:Boolean = false
    private val COMPLETED_ONBOARDING_PREF_NAME:String = "isNeedHelp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        btn_submit_auth.setOnClickListener { submitLogin() }

        img_biometric_pass.setOnClickListener {
            BiometricalAuth.initBiometrical(this)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            registerReceiver(ReceiverConnectionChanged(),IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        }else{
            registerReceiver(ReceiverConnectionChanged(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }

        BiometricalAuth.initBiometrical(this)
    }

    private fun submitLogin(){
        val emailCheck: Boolean
        val passwordCheck: Boolean

        if (Validation.isEmptyFieldEntered(edt_email_auth)){
            Validation.setErrorToEditText(edt_email_auth,"empty field")
            return
        }else{
            if (!Validation.isValidEmail(edt_email_auth.text.toString())){
                Validation.setErrorToEditText(edt_email_auth,"wrong email")
                return
            }else{
                emailCheck = true
            }
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
            getAuthorization()
        }
    }


    private fun getAuthorization(){
        if (edt_email_auth.text.trim().toString() == "zekart22@gmail.com"){

//
//            val intent = Intent(this,MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            startActivity(intent)
            showOnBoardingFragmentToUser()
        }else{
            Toast.makeText(this,getString(R.string.wrong_password_or_email),Toast.LENGTH_SHORT).show()
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
        startActivity(intent)
    }

    fun biometricAccept(){
        openNextActivity(false)
    }

    override fun onResume() {
        ReceiverConnectionChanged.connectivityReceiverListener = this
        super.onResume()
    }
}
