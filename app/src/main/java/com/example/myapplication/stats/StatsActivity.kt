package com.example.myapplication.stats

import android.os.Bundle
import android.widget.ExpandableListView.OnChildClickListener
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.manager.DataIC
import com.example.myapplication.manager.ManagerIncomeCosts
import kotlinx.android.synthetic.main.activity_stats.*


class StatsActivity : BaseActivity() {

    private var m = ManagerIncomeCosts(this, "dataStore.json")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        initViews()
    }

    override fun initViews() {
        super.initViews()

        val adapter = StatsExpandableListAdapter(applicationContext, getMockStats())
        expListView.setAdapter(adapter)
        expListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            callSelectedCategory(groupPosition, childPosition)
        }
    }

    private fun getMockStats(): List<DataIC> {
        val out = m.readJSON()
        var balance_sum = 0
        for (i in out) {
            balance_sum += i.amount!!
        }

        balance.text = balance_sum.toString()
        return out
    }

    private fun callSelectedCategory(positionGroup: Int, positionChild: Int): Boolean {
        val bundle = Bundle().apply {
            putString("position_category", expListView.selectedPosition.toString())
        }
        val dialogFragment = FragmentViewCategory().apply {
            arguments = bundle
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        dialogFragment.show(fragmentTransaction, "dialog_category")

        return true
    }
}
