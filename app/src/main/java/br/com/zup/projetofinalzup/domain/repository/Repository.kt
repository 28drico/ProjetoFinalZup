package br.com.zup.projetofinalzup.domain.repository

import br.com.zup.projetofinalzup.data.datasource.local.Dao.FavoriteListDAO
import br.com.zup.projetofinalzup.data.datasource.remote.Response
import br.com.zup.projetofinalzup.data.datasource.remote.RetrofitService
import br.com.zup.projetofinalzup.data.datasource.teste.Item

class Repository(private val dao: FavoriteListDAO){

    suspend fun getMenuAPI():Response{
        return RetrofitService.apiService.getMenu()
    }

    fun updateFavoritedList(menu:Item){
        dao.updateFavoritedItems(menu)
    }
    fun getFavoritedList():List<Item> = dao.getFavoritedItems()

    fun insertDatabaseList(list:List<Item>){
        dao.insertList(list)
    }
    fun getLocalList():List<Item> = dao.getMenu()
}