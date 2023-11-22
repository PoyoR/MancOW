package com.poyo.ow.Ruleta.Interface

interface RuletaPresenter {

    fun showMsgDialog(msg: String)

    fun getSuerte(url: String)
    fun setSuerte(txt: String, heroe: Int, mapa: Int)
}