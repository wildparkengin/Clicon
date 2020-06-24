package ua.willeco.clicon.utility.camera

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QrAnalization(private val onQrCodesDetected: (qrCodes: List<Barcode>) -> Unit):ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC)
            .build()
        val scanner = BarcodeScanning.getClient(options)
        val media = imageProxy.image
        if (media != null) {
            val image = InputImage.fromMediaImage(media, 90)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    onQrCodesDetected(barcodes)
                    imageProxy.close()
                }
                .addOnFailureListener {
                    Log.d("qr", "something went wrong", it)
                }
                .addOnCanceledListener {
                    Log.d("qr", "OnCancel")
                }
        }
    }
}