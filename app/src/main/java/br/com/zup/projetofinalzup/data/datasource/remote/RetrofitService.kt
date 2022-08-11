package br.com.zup.projetofinalzup.data.datasource.remote

import br.com.zup.projetofinalzup.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {
    companion object {
        const val BASE_URL = "https://fun-points-api.herokuapp.com/"

        private val retrofit: Retrofit by lazy {

            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logInterceptor)
            }

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }

        @JvmStatic
        val apiService: MenuItemAPI
            get() = retrofit.create(MenuItemAPI::class.java)
    }
}