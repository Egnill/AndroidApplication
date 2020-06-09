package com.example.myapplication.debts

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_debts.*
import kotlinx.android.synthetic.main.activity_debts.tabs

class DebtsActivity : BaseActivity(), DebtAddedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debts)
        initViews()
        reloadData()
    }

    override fun initViews() {
        super.initViews()

        tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                reloadData()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // do nothing
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility == View.VISIBLE) {
                    fab?.hide()
                } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                    fab?.show()
                }
            }
        })

        fab.setOnClickListener {
            val debtType = DebtType.fromInt(tabs.selectedTabPosition)
            AddDebtDialogFragment.newInstance(debtType).show(supportFragmentManager,  null)
        }
    }

    override fun onDebtAdded() {
        reloadData()
    }

    private fun reloadData() {
        val debtType = DebtType.fromInt(tabs.selectedTabPosition)
        val data = dataStorage.readJSON().filter { it.variable == debtType.toString() }
        recyclerView.adapter = DebtsListAdapter(data)
    }
}
