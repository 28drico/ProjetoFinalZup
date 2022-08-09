package br.com.zup.projetofinalzup.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.projetofinalzup.data.datasource.local.Dao.FavoriteListDAO

abstract class FavoriteListDatabase : RoomDatabase() {
    abstract fun favoriteListDAO(): FavoriteListDAO

    companion object {
        @Volatile
        private var INSTANCE: FavoriteListDatabase? = null

        fun getDatabase(context: Context): FavoriteListDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteListDatabase::class.java,
                    "restaurant_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}