package com.wildpark.clicon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager

import android.widget.Toast
import com.wildpark.clicon.utility.Validation
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity() {

    private var doubleBackPressed:Boolean = false
    private val COMPLETED_ONBOARDING_PREF_NAME:String = "isNeedHelp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        btn_submit_auth.setOnClickListener { submitLogin() }
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
            Intent(this,MainActivity::class.java)
        }else{
            Intent(this,MainActivity::class.java)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}
