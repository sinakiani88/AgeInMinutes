package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.btnDatePicker)
        button?.setOnClickListener { view->
            clickDatePicker(view)

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear,
                                                                              selectedMonth,
                                                                              selectedDayOfMonth ->
            Toast.makeText(this, "the chosen year is $selectedYear, month is $selectedMonth, day " +
                    "is $selectedDayOfMonth ", Toast.LENGTH_LONG)
                    .show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            val tvSelect = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelect.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000
            val differentInMinutes = currentDateToMinutes - selectedDateInMinutes

            val tvSelectInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
            tvSelectInMinutes.text = differentInMinutes.toString()
        }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 84600000
        dpd.show()
    }
}



