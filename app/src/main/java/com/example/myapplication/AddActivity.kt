package com.example.myapplication

import android.os.Bundle
import android.widget.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_add.*
import java.text.*
import java.util.*

private const val INCOME_INDEX = 0
private const val COSTS_INDEX = 1

class AddActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initViews()
    }

    override fun initViews() {
        super.initViews()

        setCategories()
        setDate()

        add_money.setOnClickListener {
            addRecord()
            clearField()
        }

        tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setCategories()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // do nothing
            }
        })
    }

    private fun setCategories() {
        val categories = when (tabs.selectedTabPosition) {
            INCOME_INDEX -> resources.getStringArray(R.array.array_income)
            COSTS_INDEX -> resources.getStringArray(R.array.array_category)
            else -> throw IllegalArgumentException()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        category_text_view.setAdapter(adapter)
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

    private fun addRecord() {
        val amount = text_amount.editText?.text.toString().toInt() * getSign()
        val category = category.editText?.text.toString()
        val comment = comment.editText?.text.toString()
        val date = data.editText?.text.toString()
        val time = time.editText?.text.toString()
        val variable = when (tabs.selectedTabPosition) {
            INCOME_INDEX -> getString(R.string.income)
            COSTS_INDEX -> getString(R.string.costs)
            else -> null
        }
        dataStorage.writeJSON(amount, category, comment, date, time, variable)
    }

    private fun clearField() {
        text_amount.editText?.text?.clear()
        category.editText?.text?.clear()
        comment.editText?.text?.clear()
    }

    private fun getSign(): Int {
        return when (tabs.selectedTabPosition) {
            INCOME_INDEX -> 1
            COSTS_INDEX -> -1
            else -> 0
        }
    }
}
