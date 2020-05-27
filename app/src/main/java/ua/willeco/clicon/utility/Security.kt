package ua.willeco.clicon.utility

import android.util.Base64
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object Security {

    private var CRYPT_METHOD = "AES/ECB/PKCS5Padding"

    fun cryptPasswordAES(password:String):String{
        try {
            val cipher = Cipher.getInstance(CRYPT_METHOD)
            cipher.init(Cipher.ENCRYPT_MODE, getKey())
            return Base64.encodeToString(
                cipher.doFinal(password.toByteArray(StandardCharsets.UTF_8)),
                Base64.DEFAULT)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun decryptValueAES(value:String):String{
        try {
            val cipher = Cipher.getInstance(CRYPT_METHOD)
            cipher.init(Cipher.DECRYPT_MODE, getKey())
            return String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun bcRypt(password:String):String{
        val hal = BCrypt.hashpw(password, BCrypt.gensalt(10))
        return "1: $hal"
    }

    private fun getKey(): SecretKeySpec? {
        val sha: MessageDigest
        try {
            var key = "aZ&2FEtE2F8uqekr".toByteArray(StandardCharsets.UTF_8)
            sha = MessageDigest.getInstance("SHA-1")
            key = sha.digest(key)
            key = key.copyOf(16)
            return SecretKeySpec(key,"AES")
        }catch (e: NoSuchAlgorithmException){
            e.printStackTrace()
        }
        return null
    }
}