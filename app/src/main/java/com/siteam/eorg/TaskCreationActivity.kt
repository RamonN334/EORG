package com.siteam.eorg

import android.app.DatePickerDialog
import android.app.DatePickerDialog.*
import android.app.TimePickerDialog.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.siteam.eorg.Utils.Task
import java.util.*

class TaskCreationActivity : AppCompatActivity(), OnClickListener, OnDateSetListener {

    lateinit var tvChosenDate: TextView

    lateinit var btnCngDate: Button
    lateinit var btnCancel: Button
    lateinit var btnOk: Button

    lateinit var dateTime: Calendar

    lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_creation)

        tvChosenDate = findViewById(R.id.chosenDate) as TextView
        btnCngDate = findViewById(R.id.btnChgDate) as Button
        btnCngDate.setOnClickListener(this)

        btnCancel = findViewById(R.id.btnCancel) as Button
        btnCancel.setOnClickListener(this)

        btnOk = findViewById(R.id.btnOk) as Button
        btnOk.setOnClickListener(this)

        dateTime = Calendar.getInstance()
        task = Task(this.intent.getStringExtra("catId"))
        InitDate()

    }

    fun checkParam() {

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
                finish()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateTime.set(Calendar.YEAR, year)
        dateTime.set(Calendar.MONTH, month)
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        InitDate()
    }

    private fun InitDate() {
        tvChosenDate.text = DateUtils.formatDateTime(this, dateTime.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
    }

    private fun setDate() {
        DatePickerDialog(this, this,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show()
    }
}
