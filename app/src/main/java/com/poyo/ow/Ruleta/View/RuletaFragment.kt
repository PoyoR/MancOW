package com.poyo.ow.Ruleta.View

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.poyo.ow.Clases.DialogDva
import com.poyo.ow.Clases.DialogLoading
import com.poyo.ow.Clases.DialogMessage
import com.poyo.ow.Clases.DialogSuerte
import com.poyo.ow.R
import com.poyo.ow.Ruleta.Interface.RuletaPresenter
import com.poyo.ow.Ruleta.Interface.RuletaView
import com.poyo.ow.Ruleta.Presenter.RuletaPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.adViewLogin
import kotlinx.android.synthetic.main.fragment_ruleta.*
import kotlinx.android.synthetic.main.fragment_ruleta.view.*


class RuletaFragment : Fragment(), RuletaView {

    private var presenter: RuletaPresenter? = null
    private var cargaDva: DialogDva? = null
    private var carga: DialogLoading? = null
    private var dialog: DialogMessage? = null
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_ruleta, container, false)

        val adRequest = AdRequest.Builder().build()

        presenter = RuletaPresenterImpl(this, requireActivity())
        carga = DialogLoading(requireActivity())
        cargaDva = DialogDva(requireActivity())
        dialog = DialogMessage(requireActivity())

        view.imgLuckToday.setOnClickListener {
            //Prueba ca-app-pub-3940256099942544/1033173712
            //Real ca-app-pub-4694663491994854/3764124926
            InterstitialAd.load(requireActivity(),"ca-app-pub-4694663491994854/3764124926", adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("RULETA", adError?.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("RULETA", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    mInterstitialAd?.show(requireActivity())

                    mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            Log.d("RULETA", "Ad was dismissed.")
                            val url = requireActivity().intent.getStringExtra("Url")
                            presenter!!.getSuerte(url!!)
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                            Log.d("RULETA", "Ad failed to show.")
                            val url = requireActivity().intent.getStringExtra("Url")
                            presenter!!.getSuerte(url!!)
                        }

                        override fun onAdShowedFullScreenContent() {
                            Log.d("RULETA", "Ad showed fullscreen content.")
                            mInterstitialAd = null;
                        }
                    }
                }
            })

        }



        //Cargar anuncio
        view.adViewRuleta.loadAd(adRequest)

        // Inflate the layout for this fragment
        return view
    }


    override fun showLoading() {
        carga!!.showDialog()
    }

    override fun hideLoading() {
        carga!!.hideDialog()
    }

    override fun showDialogDva(){
        cargaDva!!.showDialog()
    }

    override fun hideDialogDva() {
        cargaDva!!.hideDialog()
    }

    override fun showMsgDialog(msg: String) {
        dialog!!.showDialog(msg)
    }

    override fun setSuerte(txt: String, heroe: Int, mapa: Int) {
        val dialog = DialogSuerte(requireActivity())

        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                hideDialogDva()
                dialog.showDialog(txt, heroe, mapa)
            }
        }
        timer.start()
    }
}