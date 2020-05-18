package ua.willeco.clicon.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.http.RetrofitClient.RetrofitFactory.BASE_URL
import javax.inject.Singleton

@Module
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): ApiRequests =
        retrofit.create(ApiRequests::class.java)

}