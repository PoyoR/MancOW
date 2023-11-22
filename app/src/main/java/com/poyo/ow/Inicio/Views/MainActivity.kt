 package com.poyo.ow.Inicio.Views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.poyo.ow.Clases.DialogLoading
import com.poyo.ow.Clases.DialogMessage
import com.poyo.ow.Inicio.Interface.InicioPresenter
import com.poyo.ow.Inicio.Interface.InicioView
import com.poyo.ow.Inicio.Presenter.InicioPresenterImpl
import com.poyo.ow.InicioActivity
import com.poyo.ow.R
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(), InicioView {

    private var presenter: InicioPresenter? = null
    private var carga: DialogLoading? = null
    private var dialog: DialogMessage? = null
     private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        MobileAds.initialize(this) {}

        presenter = InicioPresenterImpl(this, context)
        carga = DialogLoading(this)
        dialog = DialogMessage(this)
        var plataforma = ""

        spinPlatform?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val item = parent.getItemAtPosition(pos)
                plataforma = item.toString()

                if (item.toString() == "PC")
                    txtIdentificador.text = "Ingresa tu Battletag"
                else if (item.toString() == "Xbox")
                    txtIdentificador.text = "Ingresa tu Gamertag"
                else if (item.toString() == "Playstation")
                    txtIdentificador.text = "Ingresa tu ID"
                else
                    txtIdentificador.text = "Ingresa tu Nintendo ID"

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        btnUser.setOnClickListener {

            val usuario = edtUsuario.text.toString()
            var usuarioFinal = ""
            var platformFinal = ""
            if(plataforma == "PC"){
                usuarioFinal = usuario.replace("#", "-")
                platformFinal = "pc"
            }else if (plataforma == "Xbox"){
                usuarioFinal = usuario.replace(" ", "%20")
                platformFinal = "xbl"
            }else if (plataforma == "Playstation"){
                usuarioFinal = usuario
                platformFinal = "psn"
            }else{
                usuarioFinal = usuario
                platformFinal = "nintendo-switch"
            }

            presenter!!.verificarPerfil(usuarioFinal, platformFinal)
        }

        txtHowTo.setOnClickListener {
            dialog!!.showDialog("Para colocar tu perfil en modo público dirígete a opciones -> social -> visibilidad del perfil de carrera y selecciona la opcion \"Público\"")
        }


        //Cargar anuncio
        val adRequest = AdRequest.Builder().build()
        adViewLogin.loadAd(adRequest)
    }

     override fun showLoading() {
         carga!!.showDialog()
     }

     override fun hideLoading() {
         carga!!.hideDialog()
     }

     override fun showMsgDialog(msg: String) {
         dialog!!.showDialog(msg)
     }

     override fun goToPerfil(url: String) {
         val intent = Intent(applicationContext, InicioActivity::class.java)
         intent.putExtra("Url",url)
         startActivity(intent)
     }


 }