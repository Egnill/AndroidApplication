package com.example.myapplication

import android.os.Bundle
import android.widget.*
import com.example.myapplication.manager.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_add.*
import java.text.*
import java.util.*

class AddActivity : BaseActivity() {

    private val m = ManagerIncomeCosts(this, "dataStore.json")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initViews()
        setDate()
        add_money.setOnClickListener {
            addFun()
            clearField()
        }

        tab_ic.addOnTabSelectedListener( object :
            TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    initViews()
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
        })
    }

    override fun initViews() {
        super.initViews()

        //For example
        comment.editText?.setText("plz, work")

        val str = when (tab_ic.selectedTabPosition) {
            0 -> resources.getStringArray(R.array.array_income)
            1 -> resources.getStringArray(R.array.array_category)
            else -> throw IllegalArgumentException()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, str)
        (category.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun addFun() {
        val amount: Int = text_amount.editText?.text.toString().toInt() * getSign(tab_ic.selectedTabPosition)
        val category: String = category.editText?.text.toString()
        val comment: String? = comment.editText?.text.toString()
        val variable: String? = when (tab_ic.selectedTabPosition) {
            0 -> getString(R.string.income)
            1 -> getString(R.string.costs)
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

    private fun clearField() {
        text_amount.editText?.text?.clear()
        category.editText?.text?.clear()
        comment.editText?.text?.clear()
    }

    private fun getSign(position: Int): Int {
        return when (position) {
            0 -> 1
            1 -> -1
            else -> 0
        }
    }
}
