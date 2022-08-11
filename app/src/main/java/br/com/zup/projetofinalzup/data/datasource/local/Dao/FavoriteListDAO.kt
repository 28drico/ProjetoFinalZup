package br.com.zup.projetofinalzup.data.datasource.local.Dao

import androidx.room.*
import br.com.zup.projetofinalzup.data.model.CardapioResult

@Dao
interface FavoriteListDAO {

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCardapioFavorite(cardapio: CardapioResult)

    fun getAllCardapioFavorited(): List<CardapioResult>

}