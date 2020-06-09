package com.example.myapplication.debts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.manager.DataIC

class DebtsListAdapter(private val data: List<DataIC>) : RecyclerView.Adapter<DebtsListAdapter.DebtsViewHolder>() {

    class DebtsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stats, parent, false)
        return DebtsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DebtsViewHolder, position: Int) {
        val item = data[position]

        val category = holder.itemView.findViewById<TextView>(R.id.category)
        val amount = holder.itemView.findViewById<TextView>(R.id.amount)

        category.text = item.category
        amount.text = item.amount.toString()
    }

    override fun getItemCount() = data.size

    public fun updataItemAll () {
        notifyDataSetChanged()
    }
}