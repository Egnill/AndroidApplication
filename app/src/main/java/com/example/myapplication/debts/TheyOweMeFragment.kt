package com.example.myapplication.debts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.manager.DataIC
import com.example.myapplication.manager.ManagerIncomeCosts
import kotlinx.android.synthetic.main.activity_debts.*
import kotlinx.android.synthetic.main.fragment_they_owe_me.view.*

@Suppress("IMPLICIT_BOXING_IN_IDENTITY_EQUALS")
class TheyOweMeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_they_owe_me, container, false).apply {
            recyclerView = list_they_owe_me
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = DebtsListAdapter(getMockStats())

            list_they_owe_me.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && activity?.fab?.visibility === View.VISIBLE) {
                        activity?.fab?.hide()
                    } else if (dy < 0 && activity?.fab?.visibility !== View.VISIBLE) {
                        activity?.fab?.show()
                    }
                }
            })
        }
    }

    private fun getMockStats(): List<DataIC> {
        val manager = ManagerIncomeCosts(activity?.applicationContext!!, getString(R.string.data_debts))
        val list = manager.readJSON()
        val str = getString(R.string.they_owe_me)
        val outList = list.filter { it.variable == str }
        return outList
    }
}
