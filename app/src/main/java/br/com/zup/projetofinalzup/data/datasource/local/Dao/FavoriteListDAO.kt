package br.com.zup.projetofinalzup.data.datasource.local.dao

import androidx.room.*
import br.com.zup.projetofinalzup.data.model.MenuItem

@Dao
interface FavoriteListDAO {

    @Query("Select * From item WHERE isFavorite = 1")
    fun getFavoritedList(): List<MenuItem>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavList(item: MenuItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoDatabase(item: MenuItem)

    @Query("Select * From item")
    fun getCartList(): List<MenuItem>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCartList(item: MenuItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoCart(item: MenuItem)

}