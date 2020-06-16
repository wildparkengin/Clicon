package ua.willeco.clicon.ui

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
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.ActivityAuthorizationBinding
import ua.willeco.clicon.enums.ConnectionType
import ua.willeco.clicon.mvp.BaseActivity
import ua.willeco.clicon.mvp.contract.AuthorizationActivityContract
import ua.willeco.clicon.mvp.presenter.AuthorizationActivityPresenter
import ua.willeco.clicon.receivers.ReceiverConnectionChanged
import ua.willeco.clicon.utility.Validation


class AuthorizationActivity : BaseActivity<AuthorizationActivityPresenter>(),
    ReceiverConnectionChanged.ConnectivityReceiverListener,AuthorizationActivityContract.View{

    lateinit var binding: ActivityAuthorizationBinding
    lateinit var loaderDialog: AlertDialog

    private val PERMISSION_REQUEST_CODE = 123
    private var doubleBackPressed:Boolean = false
    private var connectionBroadcastReceiver: BroadcastReceiver? = null
    private var connectionType = ConnectionType.NOT_CONNECTION

    //TODO create permition to check WIFI network
    override fun onNetworkConnectionChanged(isLocalConnected: ConnectionType) {
        val iconTypeConnection = findViewById<ImageView>(R.id.img_type_connection)

        connectionType = isLocalConnected

        when(isLocalConnected){
            ConnectionType.NOT_CONNECTION ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,
                        R.drawable.ic_unknow_connection_mode
                    ))}
            ConnectionType.WIFI_WPECO ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,
                        R.drawable.ic_local_mode_connection
                    ))
            }
            ConnectionType.CELLURAL ->{
                iconTypeConnection.setImageDrawable(
                    ContextCompat.getDrawable(this,
                        R.drawable.ic_server_mode_connection
                    ))}

            else ->{iconTypeConnection.setImageDrawable(
            ContextCompat.getDrawable(this,
                R.drawable.ic_server_mode_connection
            ))}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
//        img_biometric_pass.setOnClickListener {
//            BiometricalAuth.initBiometrical(this)
//        }

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

    override fun onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed()
            return
        }

        this.doubleBackPressed = true

        showToast(getString(R.string.double_back_pressed))

        Handler().postDelayed({ doubleBackPressed = false }, 2000)
    }

    override fun onResume() {
        ReceiverConnectionChanged.connectivityReceiverListener = this
        super.onResume()
    }

    override fun initView() {
        loaderDialog = LoaderDialog().createProgressDialog(this)

        binding.btnSubmitAuth.setOnClickListener {
            onClickButtonAuth()
        }
    }

    override fun onClickButtonAuth() {
        presenter.validateAuthEnteredData(
            binding.edtEmailAuth.text.toString(),
            binding.edtPasswordAuth.text.toString()
        )
    }

    override fun getStateCheckBox(): Boolean {
        return binding.checkSaveUserData.isChecked
    }

    override fun showToast(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        loaderDialog.show()
    }

    override fun closeLoader() {
        loaderDialog.dismiss()
    }

    override fun setErrorMessageToLoginEditText() {
        binding.edtEmailAuth.error = "Short"
    }

    override fun setErrorMessageToPasswordEditText() {
        binding.edtPasswordAuth.error = "Short"
    }

    override fun toMainActivity() {
        try {
            val  intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }catch (e:Exception){
            e.printStackTrace()
        }
        unregisterReceiver(connectionBroadcastReceiver)
    }


    override fun instantiatePresenter(): AuthorizationActivityPresenter {
        return AuthorizationActivityPresenter(this)
    }
}
