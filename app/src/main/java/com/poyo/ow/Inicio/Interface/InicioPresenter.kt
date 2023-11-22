package com.poyo.ow.Inicio.Interface

interface InicioPresenter {

    fun showLoading()
    fun hideLoading()

    fun verificarPerfil(usuario: String, plataforma: String)

    fun showMsgDialog(msg: String)

    fun goToPerfil(url: String)
}