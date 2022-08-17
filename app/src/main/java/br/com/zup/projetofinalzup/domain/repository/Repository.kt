package br.com.zup.projetofinalzup.domain.repository

import br.com.zup.projetofinalzup.data.datasource.local.dao.FavoriteListDAO
import br.com.zup.projetofinalzup.data.datasource.remote.RetrofitService
import br.com.zup.projetofinalzup.data.datasource.remote.model.MenuRequest
import br.com.zup.projetofinalzup.data.model.MenuItem

class Repository(private val dao:FavoriteListDAO){

    suspend fun getMenu(menu:MenuRequest):List<MenuItem>{
        return RetrofitService.getAPI().getMenu(menu)
    }

    fun insertIntoDatabase(item:MenuItem) = dao.insertIntoDatabase(item)

    fun updateFavList(item:MenuItem)= dao.updateFavList(item)

    fun getFavoritedList():List<MenuItem> = dao.getFavoritedList()

    fun insertToCart(item:MenuItem) = dao.insertIntoCart(item)

    fun updateCartList(item:MenuItem)= dao.updateCartList(item)

    fun getCartList():List<MenuItem> = dao.getCartList()

    fun deleteFromDatabase(item: MenuItem) = dao.deleteFromDatabase(item.name)
}