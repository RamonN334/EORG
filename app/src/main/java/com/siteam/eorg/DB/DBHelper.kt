package com.siteam.eorg.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.siteam.eorg.Utils.Task

/**
 * Created by Igor on 20.02.2018.
 */
class DBHelper(context: Context): SQLiteOpenHelper(context, "myDB", null, 1) {
    private val CATEGORY_TABLE = "Categories"
    private val TASK_TABLE = "Tasks"
    private val db: SQLiteDatabase = writableDatabase

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

    fun insertTask(t: Task) {
        val cv = ContentValues()
        cv.put("Title", t.title)
        cv.put("Description", t.description)
        cv.put("CreationTime", t.creationTime)
        cv.put("ExpirationTime", t.expirationTime)
        cv.put("CatId", t.catId)
        db.insert(TASK_TABLE, null, cv)
    }

    fun getTasksCountById(id: Int): Int = db.query(TASK_TABLE, null,
                                            "catId = ?", arrayOf(id.toString()),
                                            null, null, null).count

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}