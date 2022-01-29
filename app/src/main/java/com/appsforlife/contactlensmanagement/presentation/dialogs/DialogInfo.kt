package com.appsforlife.contactlensmanagement.presentation.dialogs

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.presentation.listeners.DialogClickListener
import com.google.android.material.textview.MaterialTextView

class DialogInfo(
    private val activity: Activity
) {
    fun createDialogInfo(info: String) {
        val builder = AlertDialog.Builder(
            activity
        )
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.layout_dialog_info, null)
        builder.setView(view)
        val dialogDelete = builder.create()
        if (dialogDelete.window != null) {
            dialogDelete.window?.setBackgroundDrawable(ColorDrawable())
        }

        val textViewInfo = view.findViewById<TextView>(R.id.tv_info)
        textViewInfo.text = info

        dialogDelete.show()
    }
}