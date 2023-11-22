package com.poyo.ow.Perfil.Interface

import com.poyo.ow.Models.Profile

interface PerfilPresenter {

    fun showMsgDialog(msg: String)
    fun getDatos(url: String?)

    fun setDatos(perfil: Profile)


}