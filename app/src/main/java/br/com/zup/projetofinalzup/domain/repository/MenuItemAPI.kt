package br.com.zup.projetofinalzup.domain.repository

import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.model.MenuRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MenuItemAPI {

    @Headers("Content-Type: application/json")
    @POST("/menu/items")
    suspend fun getMenu(@Body menu: MenuRequest): List<MenuItem>

}