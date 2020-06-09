package com.example.myapplication.debts

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.myapplication.*
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_debts.*

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class DebtsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debts)

        initViews()
    }

    @SuppressLint("InflateParams", "RestrictedApi")
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
            val bundle = Bundle().apply {
                putInt("position", tabs.selectedTabPosition)
            }
            val dialogFragment = DialogAddDebt().apply {
                arguments = bundle
            }

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            dialogFragment.show(fragmentTransaction, "dialog")
        }
    }
}
