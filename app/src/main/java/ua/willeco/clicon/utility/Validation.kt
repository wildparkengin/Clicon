package ua.willeco.clicon.utility

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Base64
import android.widget.EditText
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
         * @param editText The editText layout
         * @return The resulting of empty data in editText
         */

        fun isEmptyFieldEntered(editText: EditText):Boolean {
            val s = editText.text.toString().trim()
            return s.isEmpty()
        }

        /**
         * Method that show error type to user
         *
         * @param editText The editText layout
         * @param message The error message to show user
         */

        fun setErrorToEditText(editText: EditText,message:String){
            editText.error = message
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

        fun isValidPasswordLenth(text: String):Boolean{
            return text.length >= 4
        }

        fun bcRypt(password:String):String{
            val hal = BCrypt.hashpw(password, BCrypt.gensalt(10))
            return "1: $hal"
        }

        fun isLocationPermissionAccess(activity: Activity):Boolean{
            return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        }

        fun cryptPasswordAES(password:String):String{
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, getKey())
                return Base64.encodeToString(cipher.doFinal(password.toByteArray(StandardCharsets.UTF_8)),
                    Base64.DEFAULT)
            }catch (e:Exception){
                e.printStackTrace()
            }
            return ""
        }

        fun decryptValueAES(value:String):String{
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
                cipher.init(Cipher.DECRYPT_MODE, getKey())
                return String(cipher.doFinal(Base64.decode(value,Base64.DEFAULT)))
            }catch (e:Exception){
                e.printStackTrace()
            }
            return ""
        }

        private fun getKey(): SecretKeySpec? {
            val sha:MessageDigest
            try {
                var key = "aZ&2FEtE2F8uqekr".toByteArray(StandardCharsets.UTF_8)
                sha = MessageDigest.getInstance("SHA-1")
                key = sha.digest(key)
                key = key.copyOf(16)
                return SecretKeySpec(key,"AES")
            }catch (e:NoSuchAlgorithmException){
                e.printStackTrace()
            }
            return null
        }

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
    }

}