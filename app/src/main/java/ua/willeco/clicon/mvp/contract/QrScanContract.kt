package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.QrScanDevice

interface QrScanContract {
    interface View{
        /**
         * Method to prevent action after login auth success
         */
        fun showErrorQrIsNotValid()
        /**
         * Method to prevent action after login auth success
         */
        fun showQrIsValid()
        /**
         * Method to prevent action after login auth success
         */
        fun onClickNextScan()
        /**
         * Method to prevent action after login auth success
         */
        fun onClickAcceptScan()
    }

    interface Presenter{
        /**
         * @param instance - instance of element which calling method
         * Method to start cameraX scan qr code
         */
        fun startScan(instance: Any)
        /**
         * Method to stop scan qr and close camera
         */
        fun stopScan()
        /**
         * Validation value from qr code
         */
        fun validateQrCode(value: String?)
    }

    interface Repository{
        /**
         * Validation value from qr code
         */
        fun getQrScanValue(device:QrScanDevice)
    }
}