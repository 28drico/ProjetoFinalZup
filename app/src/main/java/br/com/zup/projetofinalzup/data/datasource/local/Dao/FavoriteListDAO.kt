package br.com.zup.projetofinalzup.data.datasource.local.dao

import androidx.room.*
import br.com.zup.projetofinalzup.data.model.MenuItem

@Dao
interface FavoriteListDAO {

    @Query("Select * From item ORDER BY name ASC")
    fun getFavoritedList(): List<MenuItem>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavList(item: MenuItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoDatabase(item: MenuItem)
}