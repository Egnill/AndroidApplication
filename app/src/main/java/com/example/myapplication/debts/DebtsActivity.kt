package com.example.myapplication.debts

import android.os.Bundle
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
    }
}
