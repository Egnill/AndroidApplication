package com.example.myapplication.debts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.stats.Stats
import com.example.myapplication.stats.StatsListAdapter
import kotlinx.android.synthetic.main.fragment_i_must.*
import java.math.BigDecimal

class IMustFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_i_must, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerView = activity!!.findViewById(R.id.list_i_must)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = StatsListAdapter(getMockStats())
    }

    private fun getMockStats(): List<Stats> {
        return listOf(
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0)),
            Stats("Example", BigDecimal(0))
        )
    }
}