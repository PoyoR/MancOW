package com.poyo.ow.Perfil.Interactor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.poyo.ow.Clases.ApiClient
import com.poyo.ow.Clases.ApiInterface
import com.poyo.ow.Clases.CheckNetwork
import com.poyo.ow.Models.Profile
import com.poyo.ow.Models.Suerte
import com.poyo.ow.Perfil.Interface.PerfilInteractor
import com.poyo.ow.Perfil.Interface.PerfilPresenter
import com.poyo.ow.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class PerfilInteractorImpl(presenter: PerfilPresenter, context: Context): PerfilInteractor {

    private val presenter: PerfilPresenter
    private val api: ApiInterface
    private var connection = CheckNetwork(context)
    private var usuario = ""

    override fun getDatos(url: String?) {
        if (connection.isInternetAvailable()){
            val call: Call<Profile?>? = api.verificarPerfil(url!!)
            usuario = url

            call!!.enqueue(object : Callback<Profile?> {
                override fun onResponse(call: Call<Profile?>, response: Response<Profile?>) {
                    if (response.isSuccessful){
                        val perfil: Profile? = response.body()

                        presenter.setDatos(perfil!!)

                    }else{
                        try {
                            Log.i("ERROR", response.errorBody()!!.string())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<Profile?>, t: Throwable) {
                    Log.i("INTERACTOR", "ERROR")
                    Log.i("Error", t.message.toString())
                    call.cancel()
                }
            })
        }else{
            presenter.showMsgDialog("Verifique su conexión a internet")
        }

    }


    init {
        this.presenter = presenter
        api = ApiClient.getClient()!!.create(ApiInterface::class.java)
    }
}