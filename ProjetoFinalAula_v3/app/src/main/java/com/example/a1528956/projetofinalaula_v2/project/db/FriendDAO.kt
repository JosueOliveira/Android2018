package com.example.a1528956.projetofinalaula_v2.project.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FriendDAO {
    @Insert
    fun insert(friend: Friend)

    @Query("DELETE FROM friend_table")
    fun deleteAll()

    @Query("SELECT * FROM friend_table")
    fun getAll():LiveData<List<Friend>>
}