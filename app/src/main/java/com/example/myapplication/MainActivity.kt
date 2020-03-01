package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
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
}
