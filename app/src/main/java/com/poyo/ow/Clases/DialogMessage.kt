package com.poyo.ow.Clases

import android.app.Activity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.poyo.ow.R

class DialogMessage(activity: Activity) {

    private var activity: Activity
    private var dialog: AlertDialog? = null


    fun showDialog(msg: String){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val v = inflater.inflate(R.layout.message_dialog, null)
        builder.setView(v)
        builder.setCancelable(false)
        val txt: TextView = v.findViewById(R.id.txtMsgDialog)
        txt.setText(msg)

        val btn: MaterialButton = v.findViewById(R.id.btnCloseDialog)

        btn.setOnClickListener { dialog!!.dismiss() }

        dialog = builder.create()
        dialog?.show()
    }

    fun hideDialog(){
        dialog?.dismiss()
    }

    init {
        this.activity = activity
    }
}