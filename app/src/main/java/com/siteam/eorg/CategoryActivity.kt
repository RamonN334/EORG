package com.siteam.eorg

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.siteam.eorg.DB.DBHelper
import com.siteam.eorg.Utils.Task
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    lateinit var dbHelper: DBHelper
    private lateinit var catId: String
    private lateinit var rvData: RecyclerView
    private lateinit var tvAdd: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        catId = intent.getStringExtra("catId")

        fab.setOnClickListener {
            val intent = Intent(this, TaskCreationActivity::class.java)
            intent.putExtra("catId", catId)
            startActivity(intent)
        }

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
            tvAdd.visibility = View.GONE
            rvData.adapter = TaskViewAdapter(dbHelper.getTasksById(catId))
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        startActivity(Intent(this, TaskCreationActivity::class.java))
    }

    class TaskViewAdapter(
            private val tasks: Array<Task>,
            private val mListener: RecyclerViewClickListener):
            RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.task_item, parent, false)
            return ViewHolder(itemView, mListener)
        }



        override fun getItemCount() = tasks.size

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val count: Int = tasks[position].title?.length!!
            if (count > 22)
                holder?.taskTitle?.text = "${tasks[position].title?.substring(0, 21)}..."
            else holder?.taskTitle?.text = "${tasks[position].title}"
            holder?.expDate?.text = tasks[position].expirationTime
        }

        class ViewHolder(itemView: View?,
                         private val mListener: RecyclerViewClickListener):
                RecyclerView.ViewHolder(itemView),
                View.OnClickListener {
            var taskTitle: TextView? = itemView?.findViewById(R.id.itTaskTitle)
            var expDate: TextView? = itemView?.findViewById(R.id.itExpDate)

            init {
                itemView?.setOnClickListener(this)
            }

            override fun onClick(v: View) {
                mListener.onClick(v, adapterPosition)
                Log.d("myLogs", "item pressed")
            }
        }
    }
    interface RecyclerViewClickListener {
        fun onClick(v: View, position: Int)
    }
}
