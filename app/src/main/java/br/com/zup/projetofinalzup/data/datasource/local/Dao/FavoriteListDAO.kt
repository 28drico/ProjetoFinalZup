package br.com.zup.projetofinalzup.data.datasource.local.dao

import androidx.room.*
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem

@Dao
interface FavoriteListDAO {

    @Query("Select * From item WHERE isFavorite = 1")
    fun getFavoritedList(): List<MenuItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteItem(item: MenuItem)
}