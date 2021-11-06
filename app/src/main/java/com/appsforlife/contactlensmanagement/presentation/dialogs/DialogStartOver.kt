package com.appsforlife.contactlensmanagement.presentation.dialogs

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.presentation.listeners.DialogClickListener

class DialogStartOver(
    private val activity: Activity,
    private val dialogClickListener: DialogClickListener,
) {

    fun createDialogStartOver() {
        val builder = AlertDialog.Builder(
            activity
        )
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.layout_dialog_start_over, null)
        builder.setView(view)
        val dialogDelete = builder.create()
        if (dialogDelete.window != null) {
            dialogDelete.window?.setBackgroundDrawable(ColorDrawable())
        }
        view.findViewById<View>(R.id.tv_yes).setOnClickListener {
            dialogClickListener.onDialogClick()
            dialogDelete.cancel()
        }

        dialogDelete.show()
    }
}