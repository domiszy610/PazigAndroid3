package com.example.pazig3


import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: MainActivity) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        val NAME = "Baza"
        val VERSION = 1
        val TABLE_NAME = "Tabela"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        p0?.execSQL("CREATE TABLE IF EXIST $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"dystans TEXT,data TEXT)")


    }
    fun insert(dystans: String, data: String) {
        var db = this.writableDatabase
        var myValues = ContentValues()
        myValues.apply {
            put("dystans", dystans)
            put("data", data)

        }
        db.insert(TABLE_NAME, null, myValues)
        db.close()
    }



    fun selectDistance(): ArrayList<String>{

        var db = this.readableDatabase
        var dane = ArrayList<String>()
        var cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        while (cursor.isAfterLast) {
            dane.add("${cursor.getString(0)} ${cursor.getString(1)} ${cursor.getString(2)}")
            cursor.moveToNext()
        }

        db.close();
        return dane
    }




    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}