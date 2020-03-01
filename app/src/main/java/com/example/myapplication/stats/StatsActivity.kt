package com.example.myapplication.stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.math.BigDecimal

class StatsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StatsListAdapter(getMockStats())
    }

    private fun getMockStats(): List<Stats> {
        return listOf(
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300)),
            Stats("Еда", BigDecimal(-100)),
            Stats("Здоровье", BigDecimal(-1200)),
            Stats("Транспорт", BigDecimal(-12300))
        )
    }
}
