package com.siteam.eorg

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.TextView
import com.siteam.eorg.DB.DBHelper
import com.siteam.eorg.Utils.Task

class CategoryActivity : AppCompatActivity(), OnClickListener {
    lateinit var dbHelper: DBHelper
    private lateinit var catId: String
    private lateinit var rvData: RecyclerView
    private lateinit var tvAdd: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        catId = intent.getStringExtra("catId")

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(this)

        rvData = findViewById(R.id.rvData)
        tvAdd = findViewById(R.id.tvAdd)

        dbHelper = DBHelper(this)
        showTasks()
    }

    override fun onResume() {
        super.onResume()
        showTasks()
    }

    private fun showTasks() {
        if (dbHelper.getTasksCountById(catId) > 0) {
            rvData.layoutManager = LinearLayoutManager(this)
            rvData.visibility = View.VISIBLE
            tvAdd.visibility = View.INVISIBLE
            rvData.adapter = TaskViewAdapter(dbHelper.getTasksById(catId))
        }
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

    class TaskViewAdapter(private val tasks: Array<Task>): RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.task_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount() = tasks.size

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val count: Int = tasks[position].title?.length!!
            if (count > 22)
                holder?.taskTitle?.text = "${tasks[position].title?.substring(0, 21)}..."
            else holder?.taskTitle?.text = "${tasks[position].title}"
            holder?.expDate?.text = tasks[position].expirationTime
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
            super.onAttachedToRecyclerView(recyclerView)
        }

        class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
            var cv: CardView? = null
            var taskTitle: TextView? = null
            var expDate: TextView? = null

            init {
                cv = itemView?.findViewById(R.id.cvItem)
                taskTitle = itemView?.findViewById(R.id.itTaskTitle)
                expDate = itemView?.findViewById(R.id.itExpDate)
            }
        }
    }
}
