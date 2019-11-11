package ua.willeco.clicon.utility

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import ua.willeco.clicon.AuthorizationActivity
import ua.willeco.clicon.R
import java.util.concurrent.Executors

class BiometricalAuth {
    companion object{
        fun initBiometrical(activity: FragmentActivity){
            val executor = Executors.newSingleThreadExecutor()

            val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    (activity as AuthorizationActivity).biometricAccept()
                    super.onAuthenticationSucceeded(result)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })

            val promtInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(" ")
                .setDescription(activity.getString(R.string.promt_auth_fingerprint))
                .setNegativeButtonText(activity.getString(R.string.promt_cancel))
                .build()

            biometricPrompt.authenticate(promtInfo)
        }



        fun isAvailableFingerPrint(context: Context): Boolean {

            var isAvailable:Boolean = false

            val fingerprintManager = BiometricManager.from(context)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Fingerprint API only available on from Android 6.0 (M)
                if (fingerprintManager.canAuthenticate()!=BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE && fingerprintManager.canAuthenticate()!= BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) { //||fingerprintManager.canAuthenticate()!=BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
                    isAvailable = true
                }
            }

            return isAvailable

        }
    }
}