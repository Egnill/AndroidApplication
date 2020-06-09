package com.example.myapplication.stats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.myapplication.*
import com.example.myapplication.manager.CashOperationData


class StatsExpandableListAdapter(context: Context, groups: List<CashOperationData>) : BaseExpandableListAdapter() {

    private val mGroups: List<List<CashOperationData>>
    private val mContext: Context
    private val SumGroup = ArrayList<Int>()
    private val SumChild = ArrayList<Int>()

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
        val array_budget = mContext.resources.getStringArray(R.array.array_budget)
        textGroup.text = array_budget[groupPosition]
        sumGroup.text = SumGroup[groupPosition].toString()
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
            amount.text = SumChild[childPosition].toString()
        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun sortCategory(inArray: List<CashOperationData>) : List<List<CashOperationData>> {
        val array_budget = mContext.resources.getStringArray(R.array.array_budget)
        val array_category = mContext.resources.getStringArray(R.array.array_category)
        val outArray = ArrayList<ArrayList<CashOperationData>>()

        for (i in array_budget) {
            val list = ArrayList(inArray.filter { it.variable == i })

            if (list.isNotEmpty()) {
                var sum_amount = 0
                for (j in list) {
                    sum_amount += j.amount!!
                }
                SumGroup.add(sum_amount)
            } else {
                SumGroup.add(0)
            }

            for (j in array_category) {
                val list_c = ArrayList(list.filter { it.category == j })

                if (list_c.isNotEmpty()) {
                    var sum_amount = 0
                    for (h in list_c) {
                        sum_amount += h.amount!!
                    }
                    SumChild.add(sum_amount)
                } else {
                    SumChild.add(0)
                }
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