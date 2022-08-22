package br.com.zup.projetofinalzup.data.datasource.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService{

    const val URL_BASE = "https://fun-points-api.herokuapp.com"

    fun getAPI(): MenuItemAPI {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        URL_BASE
    ).build().create(
        MenuItemAPI::class.java)
    }
}