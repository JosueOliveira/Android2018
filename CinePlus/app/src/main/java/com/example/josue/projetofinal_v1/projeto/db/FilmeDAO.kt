package com.example.josue.projetofinal_v1.projeto.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface FilmeDAO {

    @Insert
    fun insert(filme: Filme)

    @Update
    fun update(filme: Filme)

    @Delete
    fun delete(filme: Filme)

    @Query("DELETE FROM filme_table")
    fun deleteAll()

    @Query("SELECT * FROM filme_table")
    fun getAll():LiveData<List<Filme>>

}