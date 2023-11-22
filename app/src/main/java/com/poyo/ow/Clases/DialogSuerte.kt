package com.poyo.ow.Clases

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.poyo.ow.R

class DialogSuerte(activity: Activity) {
    private var activity: Activity
    private var dialog: AlertDialog? = null


    fun showDialog(msg: String, heroe: Int, mapa: Int){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val v = inflater.inflate(R.layout.suerte_dialog, null)
        builder.setView(v)
        builder.setCancelable(false)
        val txt: TextView = v.findViewById(R.id.txtMsgDialogLuck)
        val imgHeroe: ImageView = v.findViewById(R.id.imgHeroLuck)
        val imgMapa: ImageView = v.findViewById(R.id.imgMapLuck)
        txt.setText(msg)
        imgHeroe.setBackgroundResource(heroe)
        imgMapa.setBackgroundResource(mapa)

        val btn: MaterialButton = v.findViewById(R.id.btnCloseDialogLuck)

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