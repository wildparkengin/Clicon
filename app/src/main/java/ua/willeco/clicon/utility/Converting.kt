package ua.willeco.clicon.utility

import android.content.res.Resources
import android.text.Editable

object Converting {

    fun pxToDp(px:Int):Int{
        return (px/Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp:Int):Int{
        return (dp*Resources.getSystem().displayMetrics.density).toInt()
    }

}