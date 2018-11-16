package com.example.a1528956.projetofinalaula_v2.project.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "friend_table")
data class Friend
(
        @PrimaryKey
        @ColumnInfo(name= "id")
        val id: Int,
        @ColumnInfo(name= "nome")
        var nome: String,
        @ColumnInfo(name = "telefone")
        var telefone: String = ""
)