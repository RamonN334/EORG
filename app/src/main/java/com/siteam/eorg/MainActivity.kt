package com.siteam.eorg

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.View.*
import android.widget.TextView
import com.siteam.eorg.DB.DBHelper
import com.siteam.eorg.Utils.TextViewEx

class MainActivity : Activity(), OnClickListener {
    private lateinit var tvTaskCount: Array<TextView>
    private lateinit var lCatLayouts: Array<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DBHelper(this)
        val tf: Typeface = Typeface.createFromAsset(this.assets, "fonts/Roboto-Regular.ttf")

        tvTaskCount = arrayOf(findViewById(R.id.taskCount1),
                findViewById(R.id.taskCount2),
                findViewById(R.id.taskCount3),
                findViewById(R.id.taskCount4))



        lCatLayouts = arrayOf(findViewById(R.id.lCat1),
                findViewById(R.id.lCat2),
                findViewById(R.id.lCat3),
                findViewById(R.id.lCat4))

        for (i: Int in 0 until lCatLayouts.size) {
            lCatLayouts[i].setOnClickListener(this)
        }

        for (i: Int in 0 until tvTaskCount.size) {
            tvTaskCount[i].typeface = tf
            tvTaskCount[i].text = dbHelper.getTasksCountById(i + 1).toString()
        }

        dbHelper.close()
    }

    override fun onClick(v: View?) {
        val dbHelper = DBHelper(this)
        val intent = Intent(this, CategoryActivity::class.java)
        when (v?.id) {
            R.id.lCat1 -> {
                intent.putExtra("catId", 1)
                intent.putExtra("tasksCount", dbHelper.getTasksCountById(1))
            }
            R.id.lCat2 -> {
                intent.putExtra("catId", 2)
                intent.putExtra("tasksCount", dbHelper.getTasksCountById(2))
            }
            R.id.lCat3 -> {
                intent.putExtra("catId", 3)
                intent.putExtra("tasksCount", dbHelper.getTasksCountById(3))
            }
            R.id.lCat4 -> {
                intent.putExtra("catId", 4)
                intent.putExtra("tasksCount", dbHelper.getTasksCountById(4))
            }
        }
        dbHelper.close()
        startActivity(intent)
    }
}
