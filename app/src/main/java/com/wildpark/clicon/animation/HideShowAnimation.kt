package com.wildpark.clicon.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.wildpark.clicon.utility.Convert


object HideShowAnimation{

    fun setAnimation(view: View){

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
                        view.visibility =View.INVISIBLE
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
                            Convert.dpToPx(200),
                            ConstraintLayout.LayoutParams.MATCH_PARENT)

                        view.layoutParams = params
                        view.visibility = View.VISIBLE
                    }
                })
        }
    }
}