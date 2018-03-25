package com.siteam.eorg

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.TextView
import com.siteam.eorg.Utils.Task

val LOG_TAG: String = "myLogs"

class CategoryActivity : AppCompatActivity(), OnClickListener {
    val catId = this.intent.getStringExtra("catId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val task = Task("","","","","")
        val intent: Intent = intent
        val catId: Int = intent.getIntExtra("catId", -1)
        Log.d(LOG_TAG, catId.toString())

        val tvShow = findViewById(R.id.tvAdd) as TextView

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                val intent = Intent(this, TaskCreationActivity::class.java)
                intent.putExtra("catId", catId)
                startActivity(intent)
            }
        }
    }
}
