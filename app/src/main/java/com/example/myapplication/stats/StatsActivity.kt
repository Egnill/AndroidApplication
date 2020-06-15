package com.example.myapplication.stats

import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.dataStorageAdd
import com.example.myapplication.manager.CashOperationData

import kotlinx.android.synthetic.main.activity_stats.*
import kotlinx.android.synthetic.main.item_stats.view.*


class StatsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        initViews()
    }

    override fun initViews() {
        super.initViews()

        title = getString(R.string.stats_menu_item_text)
        val adapter = StatsExpandableListAdapter(applicationContext, getMockStats())
        expListView.setAdapter(adapter)
        expListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            callSelectedCategory(v)
        }
    }

    private fun getMockStats(): List<CashOperationData> {
        val out = dataStorageAdd.readJSON()
        balance.text = out.sumBy { it.amount!! }.toString()
        return out
    }

    private fun callSelectedCategory(v: View): Boolean {
        val bundle = Bundle().apply {
            putString("position_category", v.category.text.toString())
        }
        val dialogFragment = FragmentViewCategory().apply {
            arguments = bundle
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        dialogFragment.show(fragmentTransaction, "dialog_category")

        return true
    }
}
