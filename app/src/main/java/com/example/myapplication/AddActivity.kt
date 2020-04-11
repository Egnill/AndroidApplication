package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myapplication.manager.*
import kotlinx.android.synthetic.main.activity_add.*
import java.text.*
import java.util.*

class AddActivity : BaseActivity() {

    private var dateText: String? = null
    private var timeText: String? = null

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

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.array_category)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        category.adapter = adapter
        category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Display the selected item text on text view
                //textView_c.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    private fun addFun() {
        val amount: Int = enter_pay.text.toString().toInt()
        val category: String = category.selectedItem.toString()
        val comment: String? = enter_comments.text.toString()
        val variable: String? = when (ic_radioGroup.id) {
            R.id.income_radioButton -> "income"
            R.id.costs_radioButton -> "costs"
            else -> null
        }

        val m = ManagerIncomeCosts()
        m.set(amount, category, comment, variable)
        m.writeXML()
    }

    private fun setDate() {
        val currentDate = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        dateText = dateFormat.format(currentDate)
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeText = timeFormat.format(currentDate)

        date_field.text = dateText
        time_field.text = timeText
    }
}
