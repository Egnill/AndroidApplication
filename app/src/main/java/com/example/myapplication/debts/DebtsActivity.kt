package com.example.myapplication.debts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_debts.*


class DebtsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debts)

        initViews()
    }

    @SuppressLint("InflateParams")
    override fun initViews() {
        super.initViews()

        pager.adapter = DebtsAdapter(this)

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = when (position) {
                0 -> R.string.i_must
                1 -> R.string.they_owe_me
                else -> throw IllegalArgumentException()
            }.let { getString(it) }
        }.attach()

        fab.setOnClickListener {
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val pw = PopupWindow(
                    inflater.inflate(R.layout.fragment_add_debts, null, false),
                    1000,
                    850,
                    true
                )
            pw.showAtLocation(findViewById(R.id.debs_act), Gravity.CENTER, 0, 0)
        }

        //if (R.id.list_i_must)
    }
}
