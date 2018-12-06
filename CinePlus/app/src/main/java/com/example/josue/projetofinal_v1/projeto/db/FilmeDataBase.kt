package com.example.josue.projetofinal_v1.projeto.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch


@Database(entities = [Filme::class], version = 5)
abstract class FilmeDataBase: RoomDatabase() {

    abstract fun filmeDAO():FilmeDAO

    companion object {

        @Volatile
        private var INSTANCE: FilmeDataBase? = null
        fun getDatabase(context: Context, scope: CoroutineScope):FilmeDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FilmeDataBase::class.java,
                        "friend-database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(FriendDatabaseCalback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
    private class FriendDatabaseCalback(
            private val scope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {

                }
            }
        }

    }
}


