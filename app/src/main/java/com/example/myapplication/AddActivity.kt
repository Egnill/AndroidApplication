package com.example.myapplication

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add.*


class AddActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBar
    private var list_category: Array<String> = arrayOf("Еда", "Здоровье", "Транспорт", "Спорт")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initViews()
    }

    private fun initViews(){
        actionBar = this.supportActionBar!!
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            list_category
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        category.adapter = adapter;
        category.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                //textView_c.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
