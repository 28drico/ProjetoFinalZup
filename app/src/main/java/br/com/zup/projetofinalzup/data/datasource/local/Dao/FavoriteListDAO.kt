package br.com.zup.projetofinalzup.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem

@Dao
interface FavoriteListDAO {

    @Query("Select * From item WHERE isFavorite = 1")
    fun getFavoritedList(): LiveData<List<MenuItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteItem(item: List<MenuItem>)
}