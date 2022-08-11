package br.com.zup.projetofinalzup.data.datasource.remote

import retrofit2.http.POST

interface MenuItemAPI {
    @POST("menu")
    suspend fun getMenu(): Response
}