package com.example.myapplication.debts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.dataStorage
import com.example.myapplication.manager.DataIC
import kotlinx.android.synthetic.main.activity_debts.*
import kotlinx.android.synthetic.main.fragment_they_owe_me.view.*

@Suppress("IMPLICIT_BOXING_IN_IDENTITY_EQUALS")
class TheyOweMeFragment : Fragment(), ListController {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DebtsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_they_owe_me, container, false).apply {
            recyclerView = list_they_owe_me
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = DebtsListAdapter(getMockStats())
            recyclerView.adapter = adapter

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
        val list = dataStorage.readJSON()
        val str = getString(R.string.they_owe_me)
        return list.filter { it.variable == str }
    }

    override fun updataAdapter() {
        adapter.updataItemAll()
    }
}
