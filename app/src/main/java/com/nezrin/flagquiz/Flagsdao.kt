package com.nezrin.flagquiz

import android.annotation.SuppressLint

class Flagsdao {

    @SuppressLint("Range", "Recycle")
    fun random5Flag(vt:VeriTabaniYardimcisi):ArrayList<Flags>{
        val flagsList=ArrayList<Flags>() //flag classindan objectleri aktaririq
        val db=vt.writableDatabase //veri tabani uzerinde is gormek ucun lazimdi
        //flags tablosundan veri aliriq
        val c=db.rawQuery("SELECT*FROM flags ORDER BY RANDOM() LIMIT 5",null)
        while (c.moveToNext()){
            val flag=Flags(c.getInt(c.getColumnIndex("flag_id")),
                c.getString(c.getColumnIndex("flag_name")),
                c.getString(c.getColumnIndex("flag_image")))
            flagsList.add(flag) //flag objectine bayraqlari aktaririq
        }
        return flagsList
    }
    @SuppressLint("Range")
    fun random3WrongFlag(vt:VeriTabaniYardimcisi, flag_id:Int):ArrayList<Flags>{
        val flagsList=ArrayList<Flags>()
        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT*FROM flags WHERE flag_id!=$flag_id ORDER BY RANDOM() LIMIT 3",null)
        while (c.moveToNext()){
            val flag=Flags(c.getInt(c.getColumnIndex("flag_id")),
                c.getString(c.getColumnIndex("flag_name")),
                c.getString(c.getColumnIndex("flag_image")))
            flagsList.add(flag)
        }
        return flagsList
    }
}