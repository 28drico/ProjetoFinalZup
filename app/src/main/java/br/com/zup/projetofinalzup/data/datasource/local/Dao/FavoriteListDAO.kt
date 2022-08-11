package br.com.zup.projetofinalzup.data.datasource.local.Dao

import androidx.room.*
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem

@Dao
interface FavoriteListDAO {
    @Query("SELECT * FROM menu WHERE isFavorite = 1")
    fun getFavoritedItems():List<MenuItem>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoritedItems(item: MenuItem)

    @Query("SELECT * FROM menu ORDER BY name ASC")
    fun getMenu():List<MenuItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(characters: List<MenuItem>)
}