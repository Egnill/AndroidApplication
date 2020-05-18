package com.example.myapplication.stats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.manager.DataIC

class StatsListAdapter(private val data: List<DataIC>) : RecyclerView.Adapter<StatsListAdapter.StatsViewHolder>() {

    class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stats, parent, false)
        return StatsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val item = data[position]

        val category = holder.itemView.findViewById<TextView>(R.id.category)
        val amount = holder.itemView.findViewById<TextView>(R.id.amount)

        category.text = item.category
        amount.text = item.amount.toString()
    }

    override fun getItemCount() = data.size
}