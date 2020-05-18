package ua.willeco.clicon.utility

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.EditText
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
}