package com.example.myapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.myapplication.manager.ManagerIncomeCosts
import kotlinx.android.synthetic.main.fragment_add_debts.*
import java.text.*
import java.util.*
import com.example.myapplication.debts.ListController as ListController

class DialogAddDebt : DialogFragment() {

    private lateinit var list_controller: ListController
    private val currentDate = Date()
    private val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_debts, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add_debt.setOnClickListener {
            val context = requireContext()
            val manager = ManagerIncomeCosts(context, getString(R.string.data_debts))
            manager.writeJSON(
                amount.editText?.text.toString().toInt(),
                to_whom.editText?.text.toString(),
                text_comment.editText?.text.toString(),
                dateFormat.format(currentDate),
                timeFormat.format(currentDate),
                getPosition()
            )
            /*list_controller = if (getPosition() == getString(R.string.i_must)) {
                fragmentManager!!.findFragmentById(R.id.fragment_i_must) as ListController
            } else {
                fragmentManager!!.findFragmentById(R.id.fragment_they_owe_me) as ListController
            }
            list_controller.updataAdapter()*/
            dismiss()
        }
        button_cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun getPosition(): String {
        return when (arguments?.getInt("position")) {
            0 -> R.string.i_must
            1 -> R.string.they_owe_me
            else -> throw IllegalArgumentException()
        }.let { getString(it) }
    }
}