package br.com.zup.projetofinalzup.domain.repository

import br.com.zup.projetofinalzup.data.datasource.local.dao.FavoriteListDAO
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem

class FavoritedListRepository(private val dao:FavoriteListDAO){

    suspend fun favoriteItem(item:MenuItem){
        dao.insertFavoriteItem(item)
    }
    suspend fun getFavoritedList():List<MenuItem> = dao.getFavoritedList()
}