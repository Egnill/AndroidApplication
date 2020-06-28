package com.example.myapplication.debts

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.dataStorageDebts
import com.example.myapplication.dateFormat
import com.example.myapplication.timeFormat
import kotlinx.android.synthetic.main.fragment_add_debts.*
import java.util.*

class AddDebtDialogFragment : DialogFragment() {

    private val currentDate = Date()
    private var listener: DebtAddedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? DebtAddedListener
    }

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

        val debtType = arguments!!.getSerializable(DEBT_TYPE_KEY) as DebtType

        button_add_debt.setOnClickListener {
            dataStorageDebts.writeJSON(
                amount.editText?.text.toString().toInt(),
                to_whom.editText?.text.toString(),
                text_comment.editText?.text.toString(),
                dateFormat.format(currentDate),
                timeFormat.format(currentDate),
                debtType.toString()
            )
            listener?.onDebtAdded()
            dismiss()
        }
        button_cancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val DEBT_TYPE_KEY = "DEBT_TYPE_KEY"

        fun newInstance(debtType: DebtType): AddDebtDialogFragment {
            return AddDebtDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DEBT_TYPE_KEY, debtType)
                }
            }
        }
    }
}