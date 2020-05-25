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
import kotlinx.android.synthetic.main.fragment_i_must.*

@Suppress("IMPLICIT_BOXING_IN_IDENTITY_EQUALS")
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

        recyclerView = list_i_must
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DebtsListAdapter(getMockStats())

        list_i_must.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    private fun getMockStats(): List<DataIC> {
        val manager = ManagerIncomeCosts(activity?.applicationContext!!, getString(R.string.data_debts))
        val list = manager.readJSON()
        val range = list.indices
        val outList:MutableList<DataIC> = ArrayList()
        for (i in range){
            if (list[i].variable == getString(R.string.i_must)) outList.add(list[i])
        }
        return outList
    }
}