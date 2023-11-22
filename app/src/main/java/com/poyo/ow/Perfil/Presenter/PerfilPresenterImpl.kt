package com.poyo.ow.Perfil.Presenter

import android.content.Context
import com.poyo.ow.Models.Profile
import com.poyo.ow.Perfil.Interactor.PerfilInteractorImpl
import com.poyo.ow.Perfil.Interface.PerfilInteractor
import com.poyo.ow.Perfil.Interface.PerfilPresenter
import com.poyo.ow.Perfil.Interface.PerfilView

class PerfilPresenterImpl(view: PerfilView, context: Context): PerfilPresenter {

    private val interactor: PerfilInteractor
    private val view: PerfilView

    init {
        interactor = PerfilInteractorImpl(this, context)
        this.view = view
    }

    override fun showMsgDialog(msg: String) {
        view.showMsgDialog(msg)
    }

    override fun getDatos(url: String?) {
        view.showLoading()
        interactor.getDatos(url)
    }

    override fun setDatos(perfil: Profile) {
        view.hideLoading()
        view.setDatos(perfil)
    }



}