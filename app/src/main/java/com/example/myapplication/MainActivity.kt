package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.debts.DebtsActivity
import com.example.myapplication.manager.CashOperationData
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

    override fun onStart() {
        super.onStart()
        initPieChart()
        showPieChartMockData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initPieChart()
        showPieChartMockData()
        initViewListeners()
        checkPermission()
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

            centerText = getString(R.string.costs)
            setCenterTextSize(24f)
            setUsePercentValues(true)
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)

            animateXY(1500, 1500, Easing.EaseInSine, Easing.EaseInSine)
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
        val entries = setEntries()

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

    private fun setEntries(): List<PieEntry> {
        val inList = dataStorageAdd.readJSON()
        if (inList.isNotEmpty()) {
            val resList = inList.filter { it.amount!! < 0 }
            val outList = ArrayList<PieEntry>()
            val categoryList = resources.getStringArray(R.array.array_category)
            val allBalance = getSumCategory(resList)

            for (i in categoryList) {
                val transitionalList = inList.filter { it.category == i }
                if (transitionalList.isNotEmpty()) {
                    val sumCategory = getSumCategory(transitionalList)
                    val percentCategory = ((sumCategory * 100) / allBalance).toFloat()
                    outList.add(
                        PieEntry(
                            percentCategory,
                            i
                        )
                    )
                }
            }

            return outList
        } else {
            return listOf(
                PieEntry(
                    100F,
                    "Example"
                )
            )
        }
    }

    private fun getSumCategory(inList: List<CashOperationData>): Int {
        var sum = 0
        for (i in inList) {
            sum += i.amount!! * -1
        }
        return sum
    }

    private fun checkPermission() {
        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
    }
}
