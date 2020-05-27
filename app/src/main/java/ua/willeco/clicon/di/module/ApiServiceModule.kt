package ua.willeco.clicon.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.utility.Constants
import ua.willeco.clicon.utility.Constants.AP_RSB
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object ApiServiceModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor)

        client.readTimeout(Constants.HTTP_READ_TIMEOUT,TimeUnit.SECONDS)
        client.connectTimeout(Constants.HTTP_CONNECT_TIMEOUT,TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(AP_RSB)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): ApiRequests =
        retrofit.create(ApiRequests::class.java)

}