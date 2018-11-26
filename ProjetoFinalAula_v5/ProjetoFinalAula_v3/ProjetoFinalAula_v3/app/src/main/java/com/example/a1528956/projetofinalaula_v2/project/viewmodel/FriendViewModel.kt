package com.example.a1528956.projetofinalaula_v2.project.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.a1528956.projetofinalaula_v2.project.db.Friend
import com.example.a1528956.projetofinalaula_v2.project.db.FriendDataBase
import com.example.a1528956.projetofinalaula_v2.project.repository.FriendRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main


import kotlin.coroutines.experimental.CoroutineContext


//
class FriendViewModel(application: Application) : AndroidViewModel(application){

    private val repository: FriendRepository
    val allFriends: LiveData<List<Friend>>

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        val friendDAO = FriendDataBase.getDatabase(application, scope).friendDAO()
        repository = FriendRepository(friendDAO)
        allFriends = repository.allFriends
    }

    fun insert(friend: Friend) = scope.launch(Dispatchers.IO){
        repository.insert(friend)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}