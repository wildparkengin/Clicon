package ua.willeco.clicon.utility

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import ua.willeco.clicon.ui.AuthorizationActivity
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
                    //(activity as AuthorizationActivity).getAuthorizationRequest()
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
    }
}