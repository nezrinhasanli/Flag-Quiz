package com.nezrin.flagquiz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeriTabaniYardimcisi(context: Context):
    SQLiteOpenHelper(context,"bayrakquiz.sqlite",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
      db?.execSQL("CREATE TABLE IF NOT EXISTS 'flag'(\n"+
      "\t'flag_id'\tINTEGER PRIMARY KEY AUTOINCREMENT,\n"+
              "\t'flag_name'\tTEXT,\n"+
              "\t'flag_image'\tTEXT\n"+
      ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS flags")
        onCreate(db)
    }
}