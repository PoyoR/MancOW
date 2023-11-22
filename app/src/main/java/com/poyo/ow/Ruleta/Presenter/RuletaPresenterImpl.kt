package com.poyo.ow.Ruleta.Presenter

import android.content.Context
import com.poyo.ow.Ruleta.Interactor.RuletaInteractorImpl
import com.poyo.ow.Ruleta.Interface.RuletaInteractor
import com.poyo.ow.Ruleta.Interface.RuletaPresenter
import com.poyo.ow.Ruleta.Interface.RuletaView

class RuletaPresenterImpl(view: RuletaView, context: Context): RuletaPresenter {

    private val interactor: RuletaInteractor
    private val view: RuletaView

    init {
        interactor = RuletaInteractorImpl(this, context)
        this.view = view
    }

    override fun showMsgDialog(msg: String) {
        view.showMsgDialog(msg)
    }

    override fun getSuerte(url: String) {
        view.showDialogDva()
        interactor.getSuerte(url)
    }

    override fun setSuerte(txt: String, heroe: Int, mapa: Int) {
        view.setSuerte(txt, heroe, mapa)
    }

}