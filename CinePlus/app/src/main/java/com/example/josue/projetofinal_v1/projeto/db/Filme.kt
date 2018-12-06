package com.example.josue.projetofinal_v1.projeto.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.Intent
import java.io.Serializable
import java.sql.Blob

@Entity(tableName = "filme_table")
data class Filme (
        @ColumnInfo(name = "nome")
        var nome: String,
        @ColumnInfo(name = "sinopse")
        var sinopse: String,
        @ColumnInfo(name = "categoria")
        var categoria : Int,
        @ColumnInfo(name = "urlImage")
        var urlImage: String,
        @ColumnInfo(name = "data")
        var data: ByteArray


):Serializable{
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name= "id")
        var id: Long = 0
}