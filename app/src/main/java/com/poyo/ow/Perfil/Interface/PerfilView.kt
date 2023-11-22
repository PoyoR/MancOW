package com.poyo.ow.Perfil.Interface

import com.poyo.ow.Models.Profile

interface PerfilView {

    fun showLoading()
    fun hideLoading()

    fun showMsgDialog(msg: String)

    fun setDatos(perfil: Profile)
}