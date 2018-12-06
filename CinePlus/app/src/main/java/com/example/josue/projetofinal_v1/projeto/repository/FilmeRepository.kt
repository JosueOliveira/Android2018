package com.example.josue.projetofinal_v1.projeto.repository

import android.arch.lifecycle.LiveData
import com.example.josue.projetofinal_v1.projeto.db.Filme
import com.example.josue.projetofinal_v1.projeto.db.FilmeDAO

class FilmeRepository (private val filmeDAO: FilmeDAO) {

    val allFilmes: LiveData<List<Filme>> = filmeDAO.getAll()

    fun insert(filme: Filme){
        filmeDAO.insert(filme)
    }


    fun update(filme: Filme){
        filmeDAO.update(filme)
    }

    fun delete(filme: Filme){
        filmeDAO.delete(filme)
    }
}