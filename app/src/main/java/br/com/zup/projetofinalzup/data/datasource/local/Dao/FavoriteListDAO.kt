package br.com.zup.projetofinalzup.data.datasource.local.Dao

import androidx.room.*
import br.com.zup.projetofinalzup.data.datasource.teste.Item

@Dao
interface FavoriteListDAO {
    @Query("SELECT * FROM menu WHERE isFavorite = 1")
    fun getFavoritedItems():List<Item>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoritedItems(item: Item)

    @Query("SELECT * FROM menu ORDER BY name ASC")
    fun getMenu():List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(characters: List<Item>)
}