package com.poyo.ow.Ruleta.Interactor

import android.content.Context
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.poyo.ow.Clases.CheckNetwork
import com.poyo.ow.Models.Suerte
import com.poyo.ow.R
import com.poyo.ow.Ruleta.Interface.RuletaInteractor
import com.poyo.ow.Ruleta.Interface.RuletaPresenter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class RuletaInteractorImpl(presenter: RuletaPresenter, context: Context): RuletaInteractor {

    private val presenter: RuletaPresenter
    private var connection = CheckNetwork(context)

    init {
        this.presenter = presenter
    }

    override fun getSuerte(usuario: String) {
        val opciones: MutableList<Suerte> = mutableListOf()
        val heroes: MutableList<Int> = mutableListOf()
        heroes.add(R.drawable.ana)
        heroes.add(R.drawable.ashe)
        heroes.add(R.drawable.baptiste)
        heroes.add(R.drawable.bastion)
        heroes.add(R.drawable.brigitte)
        heroes.add(R.drawable.dva)
        heroes.add(R.drawable.doomfist)
        heroes.add(R.drawable.echo)
        heroes.add(R.drawable.genji)
        heroes.add(R.drawable.hanzo)
        heroes.add(R.drawable.junkrat)
        heroes.add(R.drawable.lucio)
        heroes.add(R.drawable.mccree)
        heroes.add(R.drawable.mei)
        heroes.add(R.drawable.mercy)
        heroes.add(R.drawable.moira)
        heroes.add(R.drawable.orisa)
        heroes.add(R.drawable.phara)
        heroes.add(R.drawable.reaper)
        heroes.add(R.drawable.reinhardt)
        heroes.add(R.drawable.roadhog)
        heroes.add(R.drawable.sigma)
        heroes.add(R.drawable.soldado)
        heroes.add(R.drawable.sombra)
        heroes.add(R.drawable.symmetra)
        heroes.add(R.drawable.torbjorn)
        heroes.add(R.drawable.tracer)
        heroes.add(R.drawable.widowmaker)
        heroes.add(R.drawable.winston)
        heroes.add(R.drawable.hammond)
        heroes.add(R.drawable.zarya)
        heroes.add(R.drawable.zenyatta)
        val heroeSuerte = List(1) { Random.nextInt(0, 31)}

        val mapas: MutableList<Int> = mutableListOf()
        mapas.add(R.drawable.blizzard_world)
        mapas.add(R.drawable.busan)
        mapas.add(R.drawable.dorado)
        mapas.add(R.drawable.eichenwalde)
        mapas.add(R.drawable.hanamura)
        mapas.add(R.drawable.habana)
        mapas.add(R.drawable.hollywood)
        mapas.add(R.drawable.colonia_lunar)
        mapas.add(R.drawable.ilios)
        mapas.add(R.drawable.junkertown)
        mapas.add(R.drawable.kings_row)
        mapas.add(R.drawable.lijiang)
        mapas.add(R.drawable.nepal)
        mapas.add(R.drawable.numbani)
        mapas.add(R.drawable.oasis)
        mapas.add(R.drawable.paris)
        mapas.add(R.drawable.rialto)
        mapas.add(R.drawable.ruta_66)
        mapas.add(R.drawable.anubis)
        mapas.add(R.drawable.volskaya)
        mapas.add(R.drawable.gibraltar)
        val mapaSuerte = List(1) { Random.nextInt(0, 20)}

        if(connection.isInternetAvailable()){
            val db = Firebase.firestore

            //Consultar las frases almacenadas

            var cont = 0
            db.collection("frases").get().addOnSuccessListener { result ->
                for (document in result) {
                    opciones.add(Suerte("${document.data["txt"]}", heroes[heroeSuerte[0]], mapas[mapaSuerte[0]]))
                    cont++
                }

                val suerteHoy = List(1) { Random.nextInt(0, cont)}
                var fechaRuleta: Timestamp

                //Actualizar fecha de server
                val city = hashMapOf(
                    "fecha" to FieldValue.serverTimestamp(),
                )

                db.collection("fechaServer").document("hoy")
                    .set(city)
                    .addOnSuccessListener { Log.d("", "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w("", "Error writing document", e) }


                //Consultar si el usuario tiene un registro
                db.collection("ruletas").whereEqualTo("usuario", usuario)
                    .get()
                    .addOnSuccessListener { documents ->
                        if (!documents.isEmpty){
                            for (document in documents) {

                                fechaRuleta = document.data["fecha"] as Timestamp
                                val heroe: Long = document.data["heroe"] as Long
                                val frase: String = document.data["frase"].toString()
                                val mapa: Long = document.data["mapa"] as Long
                                val docId = document.id

                                var hoy: Timestamp
                                db.collection("fechaServer").document("hoy")
                                    .get()
                                    .addOnSuccessListener { document ->
                                        if (document != null) {
                                            hoy = document.data?.get("fecha") as Timestamp
                                            val sdf = SimpleDateFormat("dd/M/yyyy")

                                            val fRuleta: Date = fechaRuleta.toDate()
                                            val fHoy: Date = hoy.toDate()


                                            val x = sdf.format(fRuleta)
                                            val y = sdf.format(fHoy)


                                            //Comparar fechas
                                            if (x == y){
                                                presenter.setSuerte(frase, heroe.toInt(), mapa.toInt())
                                            }else{
                                                db.collection("ruletas").document(docId).delete()

                                                val data = hashMapOf(
                                                    "usuario" to usuario,
                                                    "heroe" to opciones[suerteHoy[0]].heroe,
                                                    "mapa" to opciones[suerteHoy[0]].mapa,
                                                    "fecha" to FieldValue.serverTimestamp(),
                                                    "frase" to opciones[suerteHoy[0]].texto
                                                )

                                                db.collection("ruletas")
                                                    .add(data)
                                                    .addOnSuccessListener { documentReference ->
                                                        Log.d("SI", "DocumentSnapshot written with ID: ${documentReference.id}")
                                                        presenter.setSuerte(opciones[suerteHoy[0]].texto, opciones[suerteHoy[0]].heroe, opciones[suerteHoy[0]].mapa)
                                                    }
                                                    .addOnFailureListener { e ->
                                                        Log.w("NO", "Error adding document", e)
                                                    }
                                            }
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.d("", "get failed with ", exception)
                                    }
                            }
                        }else{

                            val data = hashMapOf(
                                "usuario" to usuario,
                                "heroe" to opciones[suerteHoy[0]].heroe,
                                "mapa" to opciones[suerteHoy[0]].mapa,
                                "fecha" to FieldValue.serverTimestamp(),
                                "frase" to opciones[suerteHoy[0]].texto
                            )

                            db.collection("ruletas")
                                .add(data)
                                .addOnSuccessListener { documentReference ->
                                    Log.d("SI", "DocumentSnapshot written with ID: ${documentReference.id}")
                                }
                                .addOnFailureListener { e ->
                                    Log.w("NO", "Error adding document", e)
                                }
                            presenter.setSuerte(opciones[suerteHoy[0]].texto, opciones[suerteHoy[0]].heroe, opciones[suerteHoy[0]].mapa)
                        }

                    }
                    .addOnFailureListener { exception ->
                        Log.w("ERROR", "Error getting documents: ", exception)
                    }

            }.addOnFailureListener { exception ->
                Log.w("FRBS", "Error getting documents.", exception)
            }
        }else{
            presenter.showMsgDialog("Verifique su conexión a internet")
        }



    }


}