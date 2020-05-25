package com.example.myapplication.debts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BaseActivity
import com.example.myapplication.DialogAddDebt
import com.example.myapplication.R
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
            val bundle = Bundle()
            val dialogFragment = DialogAddDebt()
            bundle.putBoolean("fullScreen", true)
            bundle.putBoolean("notAlertDialog", true)
            bundle.putInt("position", tabs.selectedTabPosition)
            dialogFragment.arguments = bundle
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                fragmentTransaction.remove(prev)
            }
            fragmentTransaction.addToBackStack(null)
            dialogFragment.show(fragmentTransaction, "dialog")
        }
    }
}
