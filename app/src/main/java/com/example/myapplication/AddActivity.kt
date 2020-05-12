package com.example.myapplication

import android.os.Bundle
import android.widget.*
import com.example.myapplication.manager.*
import kotlinx.android.synthetic.main.activity_add.*
import java.text.*
import java.util.*

class AddActivity : BaseActivity() {

    private val m = ManagerIncomeCosts(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initViews()
        setDate()
        add_money.setOnClickListener {
            addFun()
        }
    }

    override fun initViews() {
        super.initViews()

        //For example
        pay.editText?.setText("1000")
        comment.editText?.setText("plz, work")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.array_category))
        (category.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun addFun() {
        val amount: Int = pay.editText?.text.toString().toInt()
        val category: String = category.editText?.text.toString()
        val comment: String? = comment.editText?.text.toString()
        val variable: String? = when {
            income_radioButton.isChecked -> "income"
            costs_radioButton.isChecked -> "costs"
            else -> null
        }

        m.writeJSON(amount, category, comment, variable)
    }

    private fun setDate() {
        val currentDate = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateText = dateFormat.format(currentDate)
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val timeText = timeFormat.format(currentDate)

        data.editText?.setText(dateText)
        time.editText?.setText(timeText)
    }
}
