package com.example.myapplication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_debts.*

class DebtsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debts)

        initViews()
    }

    override fun initViews() {
        super.initViews()

        button.text = "OK"
    }
}
