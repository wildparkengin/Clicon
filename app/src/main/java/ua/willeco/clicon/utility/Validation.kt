package ua.willeco.clicon.utility

import android.text.TextUtils
import android.widget.EditText

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

    }

}