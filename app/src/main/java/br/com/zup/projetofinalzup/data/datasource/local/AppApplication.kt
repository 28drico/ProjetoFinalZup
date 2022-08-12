package br.com.zup.projetofinalzup.data.datasource.local

import android.app.Application
import androidx.room.Room
import br.com.zup.projetofinalzup.data.datasource.local.database.FavoriteListDatabase

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            FavoriteListDatabase::class.java, "database-character"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    companion object {
        private lateinit var db: FavoriteListDatabase
        fun getDB(): FavoriteListDatabase {
            return db
        }
    }
}