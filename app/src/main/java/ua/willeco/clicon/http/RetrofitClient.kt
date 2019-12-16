package ua.willeco.clicon.http

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.willeco.clicon.enums.ConnectionType
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class RetrofitClient {

    object RetrofitFactory {
        //TODO set BASE_URL
        const val BASE_URL = "https://80.252.241.65:90/server/"
        const val AP_RSB = "http://192.168.42.1/web_local/"

        fun makeRetrofitService(url:ConnectionType): Requests {

            val interceptor = HttpLoggingInterceptor()

            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor)
            val unsafeClient = unSafeOkHttpClient()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(getTypeRequest(url))
                .addConverterFactory(GsonConverterFactory.create()) //GsonConverterFactory.create() //GsonConverterFactory.create(gson)
                .client(unsafeClient.build()) //client.build()
                .build().create(Requests::class.java)
        }

        private fun getTypeRequest(type:ConnectionType):String{
            return when(type){
                ConnectionType.WIFI_WPECO -> AP_RSB
                else -> BASE_URL
            }
        }

        fun unSafeOkHttpClient() :OkHttpClient.Builder {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?){}
                    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                    override fun getAcceptedIssuers(): Array<X509Certificate>  = arrayOf()
                })

                // Install the all-trusting trust manager
                val  sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
                    okHttpClient.sslSocketFactory(
                        sslSocketFactory,
                        trustAllCerts.first() as X509TrustManager
                    )
                    okHttpClient.hostnameVerifier(HostnameVerifier { hostname, session -> true })
                }
                return okHttpClient

            } catch (e: Exception) {
                return okHttpClient
            }
        }
    }
}