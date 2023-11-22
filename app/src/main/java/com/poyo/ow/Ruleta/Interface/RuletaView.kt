package com.poyo.ow.Ruleta.Interface

import com.poyo.ow.Models.Profile

interface RuletaView {

    fun showLoading()
    fun hideLoading()

    fun showDialogDva()
    fun hideDialogDva()

    fun showMsgDialog(msg: String)

    fun setSuerte(txt: String, heroe: Int, mapa: Int)
}