package com.siteam.eorg.DB

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Igor on 20.02.2018.
 */
class DBHelper(context: Context): SQLiteOpenHelper(context, "myDB", null, 1) {
    private val CATEGORY_TABLE: String = "Categories"
    private val TASK_TABLE: String = "Tasks"
    private var db: SQLiteDatabase

    init {
        this.db = this.writableDatabase
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table " + CATEGORY_TABLE + "(" +
                "Id integer primary key autoincrement" + ");")
        db?.execSQL("create table " + TASK_TABLE + "(" +
                "Id integer primary key autoincrement," +
                "Title text," +
                "Description text," +
                "CreationTime text," +
                "ExpirationTime text," +
                "CatId integer," +
                "FOREIGN KEY(CatId) REFERENCES " + CATEGORY_TABLE + "(Id)" + ");")
    }

    fun getTasksCountById(id: Int): Int {
        var c: Cursor
        c = db.query(TASK_TABLE, null, "catId = ?", arrayOf(id.toString()), null, null, null)
        return c.count
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}