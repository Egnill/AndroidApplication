package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.manager.ManagerIncomeCosts
import kotlinx.android.synthetic.main.fragment_add_debts.*

class DialogAddDebt : DialogFragment() {

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
                getPosition()
            )
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