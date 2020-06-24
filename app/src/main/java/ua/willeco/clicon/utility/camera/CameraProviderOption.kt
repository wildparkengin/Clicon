package ua.willeco.clicon.utility.camera

import android.util.DisplayMetrics
import android.util.Log
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import java.lang.Math.*

class CameraProviderOption{

    companion object {
        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
        private var lensFacing: Int = CameraSelector.LENS_FACING_BACK

        fun getCameraSelector():CameraSelector{
            return CameraSelector.Builder().requireLensFacing(lensFacing).build()
        }

        fun getMetrics(viewFinder:PreviewView): DisplayMetrics {
            return DisplayMetrics().also { viewFinder.display.getRealMetrics(it)
                Log.d("qr", "Screen metrics: ${it.widthPixels} x ${it.heightPixels}")}
        }

        fun getAspectRatio(metrics:DisplayMetrics): Int {
            return aspectRatio(
                metrics.widthPixels,
                metrics.heightPixels
            ).also {
                Log.d("qr", "Preview aspect ratio: $it")
            }
        }

        fun getImgCapture(): ImageCapture {
            return ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()
        }

        /**
         *  [androidx.camera.core.ImageAnalysisConfig] requires enum value of
         *  [androidx.camera.core.AspectRatio]. Currently it has values of 4:3 & 16:9.
         *
         *  Detecting the most suitable ratio for dimensions provided in @params by counting absolute
         *  of preview ratio to one of the provided values.
         *
         *  @param width - preview width
         *  @param height - preview height
         *  @return suitable aspect ratio
         */
        private fun aspectRatio(width: Int, height: Int): Int {
            val previewRatio = max(width, height).toDouble() / min(width, height)
            if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
                return AspectRatio.RATIO_4_3
            }
            return AspectRatio.RATIO_16_9
        }
        /** Returns true if the device has an available back camera. False otherwise */
        fun hasBackCamera(cameraProvider: ProcessCameraProvider): Boolean {
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false
        }

    }
}