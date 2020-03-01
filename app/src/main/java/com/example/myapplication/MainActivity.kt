package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.stats.StatsActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initPieChart()
        showPieChartMockData()
        initViewListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true

        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        navView = findViewById(R.id.nav_view)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun initPieChart() {
        pieChart = findViewById(R.id.pie_chart)
        pieChart.run {
            description.isEnabled = false
            rotationAngle = 0F
            isRotationEnabled = true
            isHighlightPerTapEnabled = true

            setUsePercentValues(true)
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)

            animateY(1400, Easing.EaseInOutQuad)
        }
    }

    private fun initViewListeners() {
        navView.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            startOtherActivity(menuItem.itemId)
            true
        }
    }

    private fun startOtherActivity(menuItemId: Int) {
        val intent = when (menuItemId) {
            R.id.add_menu_item -> Intent(this, AddActivity::class.java)
            R.id.stats_menu_item -> Intent(this, StatsActivity::class.java)
            R.id.debts_menu_item -> Intent(this, DebtsActivity::class.java)
            else -> null
        }
        startActivity(intent)
    }

    private fun showPieChartMockData() {
        val entries = listOf(
            PieEntry(
                25F,
                "Транспорт"
            ),
            PieEntry(
                25F,
                "Развлечения"
            ),
            PieEntry(
                50F,
                "Еда"
            )
        )

        val dataSet = PieDataSet(entries, "Статистика").apply {
            setDrawIcons(false)
            sliceSpace = 3f
            iconsOffset = MPPointF(0F, 40F)
            selectionShift = 5f
            colors = ColorTemplate.COLORFUL_COLORS.toList()
        }

        val data = PieData(dataSet).apply {
            setValueFormatter(PercentFormatter(pieChart))
            setValueTextSize(11f)
            setValueTextColor(Color.WHITE)
        }

        pieChart.data = data
        pieChart.highlightValues(null)
        pieChart.invalidate()
    }
}
