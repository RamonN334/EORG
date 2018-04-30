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
                "_id integer primary key autoincrement" + ");")
        db?.execSQL("create table " + TASK_TABLE + "(" +
                "_id integer primary key autoincrement," +
                "title text," +
                "description text," +
                "creationTime text," +
                "expirationTime text," +
                "catId integer," +
                "FOREIGN KEY(CatId) REFERENCES " + CATEGORY_TABLE + "(_id)" + ");")
    }

    fun insertTask(t: Task) {
        val cv = ContentValues()
        cv.put("title", t.title)
        cv.put("description", t.description)
        cv.put("creationTime", t.creationTime)
        cv.put("expirationTime", t.expirationTime)
        cv.put("catId", t.catId)
        db.insert(TASK_TABLE, null, cv)
    }

    fun getTasksCountById(id: String): Int = db.query(TASK_TABLE, null,
            "catId = ?", arrayOf(id),
            null, null, null).count

    fun getTasksById(id: String): Array<Task> {
        val cursor = db.query(TASK_TABLE, null,
                "catId = ?", arrayOf(id),
                null, null, null)
        var tasks: Array<Task> = arrayOf()
        if (cursor.moveToFirst()) {
            do {
                val task = Task(cursor.getString(cursor.getColumnIndex("Title")),
                        cursor.getString(cursor.getColumnIndex("Description")),
                        cursor.getString(cursor.getColumnIndex("CreationTime")),
                        cursor.getString(cursor.getColumnIndex("ExpirationTime")),
                        cursor.getString(cursor.getColumnIndex("CatId")))
                tasks += task
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return tasks
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /*db?.beginTransaction()
        try {
            db?.execSQL("drop table " + TASK_TABLE)
            db?.execSQL("drop table " + CATEGORY_TABLE)

            db?.execSQL("create table " + CATEGORY_TABLE + "(" +
                    "_id integer primary key autoincrement" + ");")
            db?.execSQL("create table " + TASK_TABLE + "(" +
                    "_id integer primary key autoincrement," +
                    "title text," +
                    "description text," +
                    "creationTime text," +
                    "expirationTime text," +
                    "catId integer," +
                    "FOREIGN KEY(CatId) REFERENCES " + CATEGORY_TABLE + "(_id)" + ");")

            db?.setTransactionSuccessful()
        }
        finally {
            db?.endTransaction()
        }*/

    }
}