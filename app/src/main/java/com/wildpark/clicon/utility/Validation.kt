package com.wildpark.clicon.utility

import android.text.TextUtils
import android.widget.EditText

class Validation {

    companion object{

        fun isEmptyFieldEntered(editText: EditText):Boolean {
            val s = editText.text.toString().trim()
            return s.isEmpty()
        }

        fun setErrorToEditText(editText: EditText,message:String){
            editText.error = message
        }

        fun clearErrorToEditText(editText: EditText){
            editText.error = ""
        }

        fun isValidEmail(text:String):Boolean{
            return !TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
        }

        fun isValidPasswordLenth(text: String):Boolean{
            return text.length >= 4
        }

    }

}