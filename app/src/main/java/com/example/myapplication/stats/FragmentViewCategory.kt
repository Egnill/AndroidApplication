package com.example.myapplication.stats

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.manager.DataIC
import com.example.myapplication.manager.ManagerIncomeCosts
import kotlinx.android.synthetic.main.activity_stats.*
import kotlinx.android.synthetic.main.fragment_view_category.*

class FragmentViewCategory : DialogFragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_category, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_title.text = arguments?.getString("position_category")
        val adapter = FragmentListAdapter(getMockStats())
        recyclerView = list_in_fragment_view_category
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun getMockStats(): List<DataIC> {
        val m = ManagerIncomeCosts( requireContext(), "dataStore.json")
        return m.readJSON()
    }
}