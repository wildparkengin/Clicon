package ua.willeco.clicon.utility

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import android.util.Base64
import android.widget.EditText
import androidx.biometric.BiometricManager
import androidx.core.content.ContextCompat
import org.mindrot.jbcrypt.BCrypt
import ua.willeco.clicon.R
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


/**
 * The Class that responsible to validation data
 */

class Validation {

    companion object{

        /**
         * Method that check input field to empty value
         *
         * @param editTextValue data from editText
         * @return The resulting of empty data in editText
         */

        fun isEmptyFieldEntered(editTextValue: String):Boolean {
            return editTextValue.isEmpty()
        }


        /**
         * Method that check input email field
         *
         * @param text The input value from email editText
         * @return The resulting of valid email
         */

        fun isValidEmail(text:String):Boolean{
            return !TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
        }

        /**
         * Method that check input password
         *
         * @param text The password editText layout
         * @return The resulting of input password data editText
         */

        fun isValidTextInputData(text: String):Boolean{
            return text.length >= 4
        }

        fun isLocationPermissionAccess(activity: Activity):Boolean{
            return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        }

        //TODO delete after use usable pattern
        fun validationRequestErrors(context: Context,type:Any):String{
            return if (type is Int){
                when(type){
                    10->{context.getString(R.string.error_code10)}
                    11->{context.getString(R.string.error_code11)}
                    else -> context.getString(R.string.error_code_unexpected)
                }
            }else
                type.toString()
        }

        fun isAvailableFingerPrint(context: Context): Boolean {
            var isAvailable:Boolean = false
            val fingerprintManager = BiometricManager.from(context)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Fingerprint API only available on from Android 6.0 (M)
                if (fingerprintManager.canAuthenticate()!= BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE && fingerprintManager.canAuthenticate()!= BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) { //||fingerprintManager.canAuthenticate()!=BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
                    isAvailable = true
                }
            }
            return isAvailable

        }
    }

}