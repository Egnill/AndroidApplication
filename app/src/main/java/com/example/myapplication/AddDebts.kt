package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.manager.ManagerIncomeCosts
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_debts.*
import kotlinx.android.synthetic.main.fragment_add_debts.*

/**
 * A simple [Fragment] subclass.
 */
class AddDebts : Fragment() {

    private val manager = ManagerIncomeCosts(activity!!.baseContext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        button_add_debt.setOnClickListener{
            manager.writeJSON(
                amount.editText?.text.toString().toInt(),
                to_whom.editText?.text.toString(),
                text_comment.editText?.text.toString(),
                getPosition(),
                "dataDebts.json"
            )
        }
        button_cancel.setOnClickListener{

        }
        return inflater.inflate(R.layout.fragment_add_debts, container, false)
    }

    private fun getPosition(): String{
        var pos = ""
        TabLayoutMediator(tabs, pager) { tab, position ->
            pos = when (position) {
                0 -> R.string.i_must
                1 -> R.string.they_owe_me
                else -> throw IllegalArgumentException()
            }.let { getString(it) }
        }.attach()
        return pos
    }
}
