package br.com.zup.projetofinalzup.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.zup.projetofinalzup.data.datasource.local.dao.FavoriteListDAO
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem

@Database(entities = [MenuItem::class],version = 1)
abstract class FavoriteListDatabase : RoomDatabase() {

    abstract fun favoriteListDAO(): FavoriteListDAO

}