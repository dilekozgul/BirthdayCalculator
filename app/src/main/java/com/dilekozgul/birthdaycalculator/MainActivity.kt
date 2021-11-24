package com.dilekozgul.birthdaycalculator

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFA4207")))
        //window.navigationBarColor = ContextCompat.getColor(this, R.color.navigationBarColor)

        tvSelectedDate.text=""
        tvDays.text=""
        tvDaysAfter.isVisible=false
        tvSelect.isVisible=false

        btnSelectDate.setOnClickListener {view->
            clickedOnDatePicker(view)


        }
    }
    fun clickedOnDatePicker (view: View) {

        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val myDatePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            //Toast.makeText(this, "Calendar selection is working", Toast.LENGTH_LONG).show()

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate.text = selectedDate

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis())).time
            val currentDateInDays = currentDate / 86400000

            val theDate = dateFormat.parse(selectedDate)

            val differenceInDays =   (theDate.time / (24 * 60 * 60 * 1000))-currentDateInDays

            tvDays.text = "$differenceInDays"

        }, currentYear, currentMonth, currentDay)

        myDatePickerDialog.datePicker.minDate = Date().time+86400000
        //myDatePickerDialog.datePicker.maxDate = Date().time - 86400000

        myDatePickerDialog.show()

        tvDaysAfter.isVisible=true
        tvSelect.isVisible=true

    }


    }

