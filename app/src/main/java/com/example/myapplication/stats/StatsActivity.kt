package com.example.myapplication.stats

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.manager.DataIC
import com.example.myapplication.manager.ManagerIncomeCosts
import java.math.BigDecimal

class StatsActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private var m = ManagerIncomeCosts(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        initViews()
    }

    override fun initViews() {
        super.initViews()

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StatsListAdapter(getMockStats())
    }

    private fun getMockStats(): List<DataIC> {
        val r = m.readJSON("dataStore.json")
        return r
    }
}
