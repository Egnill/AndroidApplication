package com.example.myapplication

import android.os.Bundle
import com.example.myapplication.animation.*

class DebtsActivity : BaseActivity() {

    private lateinit var  adapter: CollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debts)

        initViews()
    }

    override fun initViews() {
        super.initViews()
    }
}
