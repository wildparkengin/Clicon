package com.wildpark.clicon.utility

import android.content.res.Resources

object Convert {

    fun pxToDp(px:Int):Int{
        return (px/Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp:Int):Int{
        return (dp*Resources.getSystem().displayMetrics.density).toInt()
    }
}