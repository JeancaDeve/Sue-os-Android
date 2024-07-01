package com.codycod.dreamsreservation.utils

import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.EditText
import java.util.Locale

class FunctionsUI {
    companion object {
        //to convert a edit text in a field to set date

        fun showDatePickerDialog(editText: EditText, context: Context) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(selectedYear, selectedMonth, selectedDay)
                    val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    editText.setText(formattedDate.format(selectedDate.time))
                }, year, month, day)


            datePickerDialog.show()

        }
    }
}