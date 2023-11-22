package com.poyo.ow.Inicio.Interactor

import android.content.Context
import android.util.Log
import com.poyo.ow.Clases.ApiClient
import com.poyo.ow.Clases.ApiInterface
import com.poyo.ow.Clases.CheckNetwork
import com.poyo.ow.Inicio.Interface.InicioInteractor
import com.poyo.ow.Inicio.Interface.InicioPresenter
import com.poyo.ow.Models.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class InicioInteractorImpl(presenter: InicioPresenter, context: Context): InicioInteractor {

    private val presenter: InicioPresenter
    private val api: ApiInterface
    private var connection = CheckNetwork(context)

    override fun verificarPerfil(usuario: String, plataforma: String) {
        if (connection.isInternetAvailable()){
            val url = "/stats/${plataforma}/${usuario}"
            val call: Call<Profile?>? = api.verificarPerfil(url)

            call!!.enqueue(object : Callback<Profile?> {
                override fun onResponse(call: Call<Profile?>, response: Response<Profile?>) {
                    if (response.isSuccessful){
                        val perfil: Profile? = response.body()

                        if (perfil?.private == true){
                            presenter.showMsgDialog("No se pudieron obtener tus stats porque tu perfil es privado")
                        }else{
                            presenter.goToPerfil(url)
                        }

                    }else{
                        try {
                            Log.i("ERROR", response.errorBody()!!.string())
                            presenter.showMsgDialog("Oops... usuario no encontrado")
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<Profile?>, t: Throwable) {
                    Log.i("Error", t.message.toString())
                    presenter.hideLoading()
                    presenter.showMsgDialog("Ocurrió un error, por favor reintente")
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