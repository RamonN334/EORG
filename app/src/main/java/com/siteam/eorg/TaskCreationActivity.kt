package com.siteam.eorg

import android.app.DatePickerDialog
import android.app.DatePickerDialog.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.view.View.*
import android.widget.*
import com.siteam.eorg.DB.DBHelper
import com.siteam.eorg.Utils.Task
import kotlinx.android.synthetic.main.activity_task_creation.*
import java.util.*

class TaskCreationActivity : AppCompatActivity(), OnClickListener, OnDateSetListener {
    lateinit var dateTime: Calendar

    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_creation)

        btnChgDate.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
        btnOk.setOnClickListener(this)

        dateTime = Calendar.getInstance()
        initDate()

        task = Task(this.intent.getStringExtra("catId"))
        task.creationTime = chosenDate.text.toString()

    }

    private fun getParamsFromView(): Boolean {
        task.title = etTitle.text.toString()
        task.description = etDesciption.text.toString()
        task.expirationTime = chosenDate.text.toString()

        return !(task.title.equals(""))
    }

    private fun printError() {
        tvError.text = getString(R.string.emptyFieldError)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnChgDate -> {
                setDate()
            }
            R.id.btnCancel -> {
                finish()
            }
            R.id.btnOk -> {
                if (getParamsFromView()) {
                    val dbHelper = DBHelper(this)
                    dbHelper.insertTask(task)
                    dbHelper.close()
                    finish()
                }
                else {
                    printError()
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateTime.set(Calendar.YEAR, year)
        dateTime.set(Calendar.MONTH, month)
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        initDate()
    }

    private fun initDate() {
        chosenDate.text = DateUtils.formatDateTime(this, dateTime.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
    }

    private fun setDate() {
        DatePickerDialog(this, this,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show()
    }
}
