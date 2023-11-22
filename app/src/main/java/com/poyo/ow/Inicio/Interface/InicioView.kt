package com.poyo.ow.Inicio.Interface

interface InicioView {

    fun showLoading()
    fun hideLoading()

    fun showMsgDialog(msg: String)

    fun goToPerfil(url: String)
}