package com.example.myapplication.stats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.manager.CashOperationData

class FragmentListAdapter(private val data: List<CashOperationData>) : RecyclerView.Adapter<FragmentListAdapter.ViewCategoryHolder>() {

    class ViewCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewCategoryHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewCategoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewCategoryHolder, position: Int) {
        val item = data[position]

        val comment = holder.itemView.findViewById<TextView>(R.id.text_comment)
        val date = holder.itemView.findViewById<TextView>(R.id.text_date)
        val time = holder.itemView.findViewById<TextView>(R.id.text_time)
        val amount = holder.itemView.findViewById<TextView>(R.id.text_amount)

        comment?.text = item.comment
        date?.text = item.date
        time?.text = item.time
        amount?.text = item.amount.toString()
    }

    override fun getItemCount() = data.size
}