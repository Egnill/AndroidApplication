package com.example.myapplication.stats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.myapplication.*
import com.example.myapplication.manager.DataIC


class StatsExpandableListAdapter(context: Context, groups: List<DataIC>) : BaseExpandableListAdapter() {

    private val mGroups: List<List<DataIC>>
    private val mContext: Context
    private val Sum = ArrayList<Int>()

    override fun getGroupCount(): Int {
        return mGroups.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return mGroups[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return mGroups[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mGroups[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean, convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView
        if (convertView == null) {
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.activity_group_view, null)
        }
        if (isExpanded) {
            //Изменяем что-нибудь, если текущая Group раскрыта
        } else {
            //Изменяем что-нибудь, если текущая Group скрыта
        }
        val textGroup = convertView?.findViewById(R.id.textGroup) as TextView
        val sumGroup = convertView.findViewById(R.id.sumGroup) as TextView
        val array_category = mContext.resources.getStringArray(R.array.array_category)
        textGroup.text = array_category[groupPosition]
        sumGroup.text = Sum[groupPosition].toString()
        return convertView
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView
        if (convertView == null) {
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_stats, null)
        }
        val category = convertView?.findViewById<TextView>(R.id.category)
        val amount = convertView?.findViewById<TextView>(R.id.amount)

        if (category != null && amount != null){
            category.text = mGroups[groupPosition][childPosition].category
            amount.text = mGroups[groupPosition][childPosition].amount.toString()
        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun sortCategory(inArray: List<DataIC>) : List<List<DataIC>> {
        val array_category = mContext.resources.getStringArray(R.array.array_category)
        val outArray = ArrayList<ArrayList<DataIC>>()

        for (i in array_category) {
            val list = ArrayList(inArray.filter { it.category == i })

            if (list.isNotEmpty()) {
                var sum_amount = 0
                for (j in list) {
                    sum_amount += j.amount!!
                }
                Sum.add(sum_amount)
            } else {
                Sum.add(0)
            }
            outArray.add(list)
        }
        return outArray
    }

    init {
        mContext = context
        mGroups = sortCategory(groups)
    }
}