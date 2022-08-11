package br.com.zup.projetofinalzup.domain.repository

import br.com.zup.projetofinalzup.data.datasource.local.Dao.FavoriteListDAO
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.data.datasource.remote.Response
import br.com.zup.projetofinalzup.data.datasource.remote.RetrofitService

class Repository(private val dao: FavoriteListDAO){

    suspend fun getMenuAPI():Response{
        return RetrofitService.apiService.getMenu()
    }

    fun updateFavoritedList(menu:MenuItem){
        dao.updateFavoritedItems(menu)
    }
    fun getFavoritedList():List<MenuItem> = dao.getFavoritedItems()

    fun insertDatabaseList(list:List<MenuItem>){
        dao.insertList(list)
    }
    fun getLocalList():List<MenuItem> = dao.getMenu()
}