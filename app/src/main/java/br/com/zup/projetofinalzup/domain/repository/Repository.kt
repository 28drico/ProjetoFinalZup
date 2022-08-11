package br.com.zup.projetofinalzup.domain.repository

import br.com.zup.projetofinalzup.data.datasource.local.Dao.FavoriteListDAO
import br.com.zup.projetofinalzup.data.model.CardapioResult

class Repository(private val dao: FavoriteListDAO){

    fun updateFavoritedList(){}

    fun getFavoritedList(){}

    fun insertDatabaseList(){}

    suspend fun getAllMoviesFavorited(): List<CardapioResult> = dao.getAllCardapioFavorited()

    suspend fun updateCardapioFavorited(cardapio: CardapioResult){
        dao.updateCardapioFavorite(cardapio)
    }

}