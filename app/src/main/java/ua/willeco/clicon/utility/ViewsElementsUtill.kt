package ua.willeco.clicon.utility

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

object ViewsElementsUtill {
    /**
     * Method that show error type to user
     *
     * @param editText The editText layout
     * @param message The error message to show user
     */

    fun setErrorToEditText(editText: EditText, message:String){
        editText.error = message
    }

    fun setAnimationToView(view: View){
        //Animation to Hide
        if (view.visibility == View.VISIBLE){
            view.animate()
                .translationX(0.0f)
                .alpha(0.0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        val params = ConstraintLayout.LayoutParams(
                            0,
                            ConstraintLayout.LayoutParams.MATCH_PARENT)

                        view.layoutParams = params
                        view.visibility = View.INVISIBLE
                    }
                })
        }else{
            //Animation to Show
            view.animate()
                .translationX(0.0f)
                .alpha(1.0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        val params = ConstraintLayout.LayoutParams(
                            Converting.dpToPx(200),
                            ConstraintLayout.LayoutParams.MATCH_PARENT)

                        view.layoutParams = params
                        view.visibility = View.VISIBLE
                    }
                })
        }
    }

    fun showShortToastMessage(context: Context?, message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToastMessage(context: Context?, message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

}