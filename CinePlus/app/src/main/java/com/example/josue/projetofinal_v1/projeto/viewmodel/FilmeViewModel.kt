package com.example.josue.projetofinal_v1.projeto.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.josue.projetofinal_v1.projeto.db.Filme
import com.example.josue.projetofinal_v1.projeto.db.FilmeDataBase
import com.example.josue.projetofinal_v1.projeto.repository.FilmeRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main

import kotlin.coroutines.experimental.CoroutineContext

class FilmeViewModel(application: Application) : AndroidViewModel(application){

    private val repository: FilmeRepository
    val allFilmes: LiveData<List<Filme>>

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        val friendDao = FilmeDataBase.
                getDatabase(application, scope).filmeDAO()

        repository = FilmeRepository(friendDao)
        allFilmes = repository.allFilmes
    }

    fun insert(filme: Filme) = scope.launch(Dispatchers.IO){
        repository.insert(filme)
    }

    fun update(filme: Filme) = scope.launch(Dispatchers.IO){
        repository.update(filme)
    }

    fun delete(filme: Filme) = scope.launch(Dispatchers.IO){
        repository.delete(filme)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}