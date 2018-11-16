package com.example.a1528956.projetofinalaula_v2.project.db
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch


@Database(entities = [Friend::class], version = 1)
abstract class FriendDataBase : RoomDatabase(){
    abstract fun friendDAO():FriendDAO

    companion object {

        @Volatile
        private var INSTANCE: FriendDataBase? = null
        fun getDatabase(context: Context, scope: CoroutineScope):FriendDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FriendDataBase::class.java,
                        "friend_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(FreindDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class FreindDatabaseCallback(
            private val scope: CoroutineScope
    ): RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{dataBase ->
                scope.launch(Dispatchers.IO){

                    populaTabela(dataBase.friendDAO())

                }
            }
        }

        fun populaTabela(friendDAO: FriendDAO){
            friendDAO.deleteAll()
            friendDAO.insert(Friend(id= 1, nome = "Juliana"))
            friendDAO.insert(Friend(id= 2, nome = "Pedro"))
            friendDAO.insert(Friend(id= 3, nome = "Ana"))
        }
    }
}