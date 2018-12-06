package com.example.josue.projetofinal_v1.projeto.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteQueryBuilder
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DBHelper(context: Context): SQLiteAssetHelper(context, DB_NAME, null, DB_VER){

    companion object {
        private val DB_NAME = "SaveBitmap"
        private val DB_VER = 1

        private val TBL_NAME = "Gallery"
        private val COL_NAME = "Name"
        private val COL_DATA = "Data"
    }

    @Throws(SQLiteException::class)
    fun addBitmap(name:String, image:ByteArray){
        val database: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_NAME, name)
        cv.put(COL_DATA, image)
        database.insert(TBL_NAME, null, cv)

    }

    fun getBitmapByName(name: String):ByteArray?{
        val db = this.writableDatabase
        val qb = SQLiteQueryBuilder()

        val sqlSelect = arrayOf(COL_DATA)
        qb.tables = TBL_NAME

        val c = qb.query(db, sqlSelect, "Name = ?", arrayOf(name), null, null, null)

        var result: ByteArray? = null

        if(c.moveToFirst()){
            do{
                result = c.getBlob(c.getColumnIndex(COL_DATA))
            }while (c.moveToNext())
        }

        return result
    }
}