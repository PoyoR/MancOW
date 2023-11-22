package com.poyo.ow.Inicio.Presenter

import android.content.Context
import com.poyo.ow.Inicio.Interactor.InicioInteractorImpl
import com.poyo.ow.Inicio.Interface.InicioInteractor
import com.poyo.ow.Inicio.Interface.InicioPresenter
import com.poyo.ow.Inicio.Interface.InicioView

class InicioPresenterImpl(view: InicioView, context: Context): InicioPresenter {

    private val interactor: InicioInteractor
    private val view: InicioView
    override fun showLoading() {
        view.showLoading()
    }

    override fun hideLoading() {
        view.hideLoading()
    }

    override fun verificarPerfil(usuario: String, plataforma: String) {
        view.showLoading()
        interactor.verificarPerfil(usuario, plataforma)
    }

    override fun showMsgDialog(msg: String) {
        view.hideLoading()
        view.showMsgDialog(msg)
    }

    override fun goToPerfil(url: String) {
        view.hideLoading()
        view.goToPerfil(url)
    }

    init {
        interactor = InicioInteractorImpl(this, context)
        this.view = view
    }

}