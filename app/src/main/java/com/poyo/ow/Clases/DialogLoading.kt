package com.poyo.ow.Clases

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.poyo.ow.R

class DialogLoading(activity: Activity) {

    private var activity: Activity
    private var dialog: AlertDialog? = null


    fun showDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.progress, null))
        builder.setCancelable(false)

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