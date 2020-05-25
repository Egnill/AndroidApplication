package com.example.myapplication

import android.content.Context
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.manager.ManagerIncomeCosts
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_debts.*

class DialogAddDebt : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (arguments != null) {
            if (arguments?.getBoolean("notAlertDialog")!!) {
                return super.onCreateDialog(savedInstanceState)
            }
        }
        val builder = android.app.AlertDialog.Builder(activity!!)
        builder.setTitle("Alert Dialog")
        builder.setMessage("Hello! I am Alert Dialog")
        builder.setPositiveButton(
            "Cool"
        ) { dialog, which -> dismiss() }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dismiss() }
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_debts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add_debt.setOnClickListener {
            val context: Context? = activity?.applicationContext
            if (context != null) {
                val manager = ManagerIncomeCosts(context, getString(R.string.data_debts))
                manager.writeJSON(
                    amount.editText?.text.toString().toInt(),
                    to_whom.editText?.text.toString(),
                    text_comment.editText?.text.toString(),
                    getPosition()
                )
                Snackbar.make(view, "Debt has been added!", Snackbar.LENGTH_LONG)
                        .show()
            } else {
                Snackbar.make(view, "ERROR!", Snackbar.LENGTH_LONG)
                        .show()
            }
        }
        button_cancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var setFullScreen = false
        if (arguments != null) {
            setFullScreen = requireNotNull(arguments?.getBoolean("fullScreen"))
        }
        if (setFullScreen) {
            setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
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