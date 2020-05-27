package ua.willeco.clicon.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.CubeGrid
import ua.willeco.clicon.R

class LoaderDialog {
    @SuppressLint("InflateParams")
    fun createProgressDialog(context: Context): AlertDialog {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.simple_progress_bar, null)
        val progrBar = mDialogView.findViewById<ProgressBar>(R.id.spin_kit)

        val typeBar: Sprite = CubeGrid()
        progrBar.indeterminateDrawable = typeBar

        val mDialog: AlertDialog
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("")

        mBuilder.setCancelable(false)
        mDialog = mBuilder.create()
        mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.tag = "LoaderDialog"
        return mDialog
    }
}