package com.example.a1528956.projetofinalaula_v2.project.repository

import android.arch.lifecycle.LiveData
import com.example.a1528956.projetofinalaula_v2.project.db.Friend
import com.example.a1528956.projetofinalaula_v2.project.db.FriendDAO

class FriendRepository(private val friendDAO: FriendDAO) {
    val allFriends: LiveData<List<Friend>> = friendDAO.getAll()

    fun insert(friend: Friend){
        friendDAO.insert(friend)
    }
}