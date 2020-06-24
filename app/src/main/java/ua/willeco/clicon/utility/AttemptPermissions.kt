package ua.willeco.clicon.utility

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class AttemptPermissions {
    companion object{
        fun cameraPermissionsGranted(context: Context) = Constants.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }
}