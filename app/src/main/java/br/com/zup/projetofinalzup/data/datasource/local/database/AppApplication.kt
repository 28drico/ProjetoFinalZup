package br.com.zup.projetofinalzup.data.datasource.local.database

import android.app.Application
import androidx.room.Room

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            FavoriteListDatabase::class.java, "database-character"
        )
            .fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()

    }

    companion object {
        private lateinit var database: FavoriteListDatabase
        fun getdatabase(): FavoriteListDatabase {
            return database
        }
    }
}