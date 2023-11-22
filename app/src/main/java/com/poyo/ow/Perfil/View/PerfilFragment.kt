package com.poyo.ow.Perfil.View

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.poyo.ow.Clases.DialogDva
import com.poyo.ow.Clases.DialogLoading
import com.poyo.ow.Clases.DialogMessage
import com.poyo.ow.Clases.DialogSuerte
import com.poyo.ow.Models.Heroe
import com.poyo.ow.Models.Profile
import com.poyo.ow.Perfil.Interface.PerfilPresenter
import com.poyo.ow.Perfil.Interface.PerfilView
import com.poyo.ow.Perfil.Presenter.PerfilPresenterImpl
import com.poyo.ow.R
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment(), PerfilView {

    private var presenter: PerfilPresenter? = null
    private var carga: DialogLoading? = null

    private var dialog: DialogMessage? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)

        presenter = PerfilPresenterImpl(this, requireActivity())
        carga = DialogLoading(requireActivity())

        dialog = DialogMessage(requireActivity())

        val url = requireActivity().intent.getStringExtra("Url")

        presenter!!.getDatos(url)

        // Inflate the layout for this fragment
        return view

    }


    override fun showLoading() {
        carga!!.showDialog()
    }

    override fun hideLoading() {
        carga!!.hideDialog()
    }

    override fun showMsgDialog(msg: String) {
        dialog!!.showDialog(msg)
    }

    override fun setDatos(perfil: Profile) {

        if (isAdded) {


            Glide.with(this)
                .load(perfil.icon)
                .fitCenter()
                .centerCrop()
                .into(imgIconUser)

            txtNomUser.text = perfil.name

            if (!perfil.ratings.isNullOrEmpty()) {
                //Colocar icono y sr de tanque
                try {
                    Glide.with(this)
                        .load(perfil.ratings[0].rankIcon)
                        .fitCenter()
                        .centerCrop()
                        .into(imgTankIcon)
                    txtTankRank.text = perfil.ratings[0].level.toString()

                    val sr = perfil.ratings[0].level
                    if (sr <= 1499){
                        txtTankDescription.text = "Te encuentras en el rango mas bajo del juego al igual que el 8% de jugadores, esto significa que sólo comprendes los conceptos básicos del juego, para subir al siguiente rango " +
                                "necesitas aprender las habilidades de los diferentes heroes y conocer los mapas de pies a cabeza para comenzar"
                        txtTankAdvice.text = "Domina al menos 1 heroe al 100%"
                    }else if (sr >= 1500 && sr <= 1999){
                        txtTankDescription.text = "Te encuentras en un rango muy bajo al igual que el 21% de jugadores, conoces las habilidades de los diferentes héroes pero haces uso de ellas indiscriminadamente" +
                                ", es decir, que no las gestionas de manera correcta y eso te perjudica, tienes conocimientos sobre las ultimates y sabes cuales pueden counterearse entre sí"
                        txtTankAdvice.text = "Gestiona mejor tus cooldowns de habilidades y ultimates"
                    }else if (sr >= 2000 && sr <= 2499){
                        txtTankDescription.text = "Tu rango es Oro al igual que la mayoría de jugadores (32%), entiendes todo el concepto del juego, sin embargo aún no logras entender cuando una teamfight esta perdida, ademas, " +
                                " sueles ir solo contra mas de 1 enemgigo a la vez lo cual te hace perder muchas partidas"
                        txtTankAdvice.text = "Entiende cuando una temfight esta perdida a fin de no desperdiciar utlimates y tiempo valioso para reagruparte"
                    }else if (sr >= 2500 && sr <= 2999){
                        txtTankDescription.text = "Como jugador de rango platino (25% jugadores), ya entiendes y manejas de forma basica, entiendes en que momento reagruparte, controlas tus cooldowns de habilidades" +
                                " y ultimates, sin embargo, posees un ego grande que te hace pensar que estas en un rango alto y eso te hace pensar que no tienes nada más por mejorar"
                        txtTankAdvice.text = "Aprende a combear siempre tus ultimates, conoce todos los counters de los heroes afin de tener ventaja sobre el equipo enemigo"
                    }else if (sr >= 3000 && sr <= 3499){
                        txtTankDescription.text = "10% de jugadores se encuentran en este rango, te encuentras en el rango intermedio entre considerado rango alto y bajo, vas por buen camino para llegar al rango mas alto, sin embargo, " +
                                " aun no conoces todos los mini combos que se pueden hacer con las diferentes habilidades de los heroes y todos los diferentes combos de ultimates, ademas, cuentas con una buena puntería" +
                                " con los héroes que asi lo requieren"
                        txtTankAdvice.text = "Pickea el héroe correcto siempre que sea necesario para hacerle counter al enemigo"
                    }else if (sr >= 3500 && sr <= 3999){
                        txtTankDescription.text = "Te encuentras en el segundo rango mas alto del juego junto con el 3% de jugadores, sin embargo, aun cometes errores simples de posicionamiento y toma de decisiones"
                        txtTankAdvice.text = "Domina al menos 3 heroes al 100% para lograr subir, mejora tu posicionamiento antes durante y despues de las teamfights, no uses ultimates de forma precipitada"
                    }else if (sr >= 4000 && sr <= 4299){
                        txtTankDescription.text = "Eres un muy buen jugador al igual que el 1% de jugadores, conoces el juego de pies a cabeza, pero dado que te nefrentas a los mejores jugadores del mundo sueles cometer errores " +
                                " dificiles de ver y que solo con la ayuda de un coach podras mejorar"
                        txtTankAdvice.text = "Mejora tu comunicación con tus compañeros de equipo"
                    }else if (sr > 4299){
                        txtTankDescription.text = "Eres un dios de Overwatch"
                    }


                } catch (e: Exception) {
                }


                //Colocar icono y sr de dps
                try {
                    Glide.with(this)
                        .load(perfil.ratings[1].rankIcon)
                        .fitCenter()
                        .centerCrop()
                        .into(imgDpsIcon)
                    txtDpsRank.text = perfil.ratings[1].level.toString()

                    val sr = perfil.ratings[1].level
                    if (sr <= 1499){
                        txtDpsDescription.text = "Te encuentras en el rango mas bajo del juego al igual que el 8% de jugadores, esto significa que sólo comprendes los conceptos básicos del juego, para subir al siguiente rango " +
                                "necesitas aprender las habilidades de los diferentes heroes y conocer los mapas de pies a cabeza para comenzar"
                        txtDpsAdvice.text = "Domina al menos 1 heroe al 100%"
                    }else if (sr >= 1500 && sr <= 1999){
                        txtDpsDescription.text = "Te encuentras en un rango muy bajo al igual que el 21% de jugadores, conoces las habilidades de los diferentes héroes pero haces uso de ellas indiscriminadamente" +
                                ", es decir, que no las gestionas de manera correcta y eso te perjudica, tienes conocimientos sobre las ultimates y sabes cuales pueden counterearse entre sí"
                        txtDpsAdvice.text = "Gestiona mejor tus cooldowns de habilidades y ultimates"
                    }else if (sr >= 2000 && sr <= 2499){
                        txtDpsDescription.text = "Tu rango es Oro al igual que la mayoría de jugadores (32%), entiendes todo el concepto del juego, sin embargo aún no logras entender cuando una teamfight esta perdida, ademas, " +
                                " sueles ir solo contra mas de 1 enemgigo a la vez lo cual te hace perder muchas partidas"
                        txtDpsAdvice.text = "Entiende cuando una temfight esta perdida a fin de no desperdiciar utlimates y tiempo valioso para reagruparte"
                    }else if (sr >= 2500 && sr <= 2999){
                        txtDpsDescription.text = "Como jugador de rango platino (25% jugadores), ya entiendes y manejas de forma basica, entiendes en que momento reagruparte, controlas tus cooldowns de habilidades" +
                                " y ultimates, sin embargo, posees un ego grande que te hace pensar que estas en un rango alto y eso te hace pensar que no tienes nada más por mejorar"
                        txtDpsAdvice.text = "Aprende a combear siempre tus ultimates, conoce todos los counters de los heroes afin de tener ventaja sobre el equipo enemigo"
                    }else if (sr >= 3000 && sr <= 3499){
                        txtDpsDescription.text = "10% de jugadores se encuentran en este rango, te encuentras en el rango intermedio entre considerado rango alto y bajo, vas por buen camino para llegar al rango mas alto, sin embargo, " +
                                " aun no conoces todos los mini combos que se pueden hacer con las diferentes habilidades de los heroes y todos los diferentes combos de ultimates, ademas, cuentas con una buena puntería" +
                                " con los héroes que asi lo requieren"
                        txtDpsAdvice.text = "Pickea el héroe correcto siempre que sea necesario para hacerle counter al enemigo"
                    }else if (sr >= 3500 && sr <= 3999){
                        txtDpsDescription.text = "Te encuentras en el segundo rango mas alto del juego junto con el 3% de jugadores, sin embargo, aun cometes errores simples de posicionamiento y toma de decisiones"
                        txtDpsAdvice.text = "Domina al menos 3 heroes al 100% para lograr subir, mejora tu posicionamiento antes durante y despues de las teamfights, no uses ultimates de forma precipitada"
                    }else if (sr >= 4000 && sr <= 4299){
                        txtDpsDescription.text = "Eres un muy buen jugador al igual que el 1% de jugadores, conoces el juego de pies a cabeza, pero dado que te nefrentas a los mejores jugadores del mundo sueles cometer errores " +
                                " dificiles de ver y que solo con la ayuda de un coach podras mejorar"
                        txtDpsAdvice.text = "Mejora tu comunicación con tus compañeros de equipo"
                    }else if (sr > 4299){
                        txtDpsDescription.text = "Eres un dios de Overwatch"
                    }
                } catch (e: Exception) {
                }


                //Colocar icono y sr de support
                try {
                    Glide.with(this)
                        .load(perfil.ratings[2].rankIcon)
                        .fitCenter()
                        .centerCrop()
                        .into(imgSupportIcon)
                    txtSupportRank.text = perfil.ratings[2].level.toString()

                    val sr = perfil.ratings[2].level
                    if (sr <= 1499){
                        txtSupportDescription.text = "Te encuentras en el rango mas bajo del juego al igual que el 8% de jugadores, esto significa que sólo comprendes los conceptos básicos del juego, para subir al siguiente rango " +
                                "necesitas aprender las habilidades de los diferentes heroes y conocer los mapas de pies a cabeza para comenzar"
                        txtSupportAdvice.text = "Domina al menos 1 heroe al 100%"
                    }else if (sr >= 1500 && sr <= 1999){
                        txtSupportDescription.text = "Te encuentras en un rango muy bajo al igual que el 21% de jugadores, conoces las habilidades de los diferentes héroes pero haces uso de ellas indiscriminadamente" +
                                ", es decir, que no las gestionas de manera correcta y eso te perjudica, tienes conocimientos sobre las ultimates y sabes cuales pueden counterearse entre sí"
                        txtSupportAdvice.text = "Gestiona mejor tus cooldowns de habilidades y ultimates"
                    }else if (sr >= 2000 && sr <= 2499){
                        txtSupportDescription.text = "Tu rango es Oro al igual que la mayoría de jugadores (32%), entiendes todo el concepto del juego, sin embargo aún no logras entender cuando una teamfight esta perdida, ademas, " +
                                " sueles ir solo contra mas de 1 enemgigo a la vez lo cual te hace perder muchas partidas"
                        txtSupportAdvice.text = "Entiende cuando una temfight esta perdida a fin de no desperdiciar utlimates y tiempo valioso para reagruparte"
                    }else if (sr >= 2500 && sr <= 2999){
                        txtSupportDescription.text = "Como jugador de rango platino (25% jugadores), ya entiendes y manejas de forma basica, entiendes en que momento reagruparte, controlas tus cooldowns de habilidades" +
                                " y ultimates, sin embargo, posees un ego grande que te hace pensar que estas en un rango alto y eso te hace pensar que no tienes nada más por mejorar"
                        txtSupportAdvice.text = "Aprende a combear siempre tus ultimates, conoce todos los counters de los heroes afin de tener ventaja sobre el equipo enemigo"
                    }else if (sr >= 3000 && sr <= 3499){
                        txtSupportDescription.text = "10% de jugadores se encuentran en este rango, te encuentras en el rango intermedio entre considerado rango alto y bajo, vas por buen camino para llegar al rango mas alto, sin embargo, " +
                                " aun no conoces todos los mini combos que se pueden hacer con las diferentes habilidades de los heroes y todos los diferentes combos de ultimates, ademas, cuentas con una buena puntería" +
                                " con los héroes que asi lo requieren"
                        txtSupportAdvice.text = "Pickea el héroe correcto siempre que sea necesario para hacerle counter al enemigo"
                    }else if (sr >= 3500 && sr <= 3999){
                        txtSupportDescription.text = "Te encuentras en el segundo rango mas alto del juego junto con el 3% de jugadores, sin embargo, aun cometes errores simples de posicionamiento y toma de decisiones"
                        txtSupportAdvice.text = "Domina al menos 3 heroes al 100% para lograr subir, mejora tu posicionamiento antes durante y despues de las teamfights, no uses ultimates de forma precipitada"
                    }else if (sr >= 4000 && sr <= 4299){
                        txtSupportDescription.text = "Eres un muy buen jugador al igual que el 1% de jugadores, conoces el juego de pies a cabeza, pero dado que te nefrentas a los mejores jugadores del mundo sueles cometer errores " +
                                " dificiles de ver y que solo con la ayuda de un coach podras mejorar"
                        txtSupportAdvice.text = "Mejora tu comunicación con tus compañeros de equipo"
                    }else if (sr > 4299){
                        txtSupportDescription.text = "Eres un dios de Overwatch"
                    }
                } catch (e: Exception) {
                }


            }

            //Obtener tiempo jugado de heroes en quickplay
            val heroes: MutableList<Heroe> = mutableListOf()
            var tmp: List<String>
            var tiempo: Int

            if (perfil.quickPlayStats.topHeroes.ana != null) {

                tmp = perfil.quickPlayStats.topHeroes.ana.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()


                heroes.add(Heroe("Ana", R.drawable.ana, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.ashe != null) {

                tmp = perfil.quickPlayStats.topHeroes.ashe.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()


                heroes.add(Heroe("Ashe", R.drawable.ashe, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.baptiste != null) {

                tmp = perfil.quickPlayStats.topHeroes.baptiste.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()

                heroes.add(Heroe("Baptiste", R.drawable.baptiste, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.bastion != null) {

                tmp = perfil.quickPlayStats.topHeroes.bastion.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()

                heroes.add(Heroe("Bastion", R.drawable.bastion, tiempo))
            }


            if (perfil.quickPlayStats.topHeroes.brigitte != null) {

                tmp = perfil.quickPlayStats.topHeroes.brigitte!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Brigitte", R.drawable.brigitte, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.dVa != null) {

                tmp = perfil.quickPlayStats.topHeroes.dVa!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("DVa", R.drawable.dva, tiempo))
            }



            if (perfil.quickPlayStats.topHeroes.doomfist != null) {
                tmp = perfil.quickPlayStats.topHeroes.doomfist.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()

                heroes.add(Heroe("Doomfist", R.drawable.doomfist, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.echo != null) {

                tmp = perfil.quickPlayStats.topHeroes.echo.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Echo", R.drawable.echo, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.genji != null) {

                tmp = perfil.quickPlayStats.topHeroes.genji.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Genji", R.drawable.genji, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.hanzo != null) {

                tmp = perfil.quickPlayStats.topHeroes.hanzo.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Hanzo", R.drawable.hanzo, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.junkrat != null) {

                tmp = perfil.quickPlayStats.topHeroes.junkrat.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Junkrat", R.drawable.junkrat, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.lucio != null) {

                tmp = perfil.quickPlayStats.topHeroes.lucio.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Lucio", R.drawable.lucio, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.mccree != null) {

                tmp = perfil.quickPlayStats.topHeroes.mccree.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Mccree", R.drawable.mccree, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.mercy != null) {

                tmp = perfil.quickPlayStats.topHeroes.mercy.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Mercy", R.drawable.mercy, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.moira != null) {

                tmp = perfil.quickPlayStats.topHeroes.moira.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Moira", R.drawable.moira, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.orisa != null) {

                tmp = perfil.quickPlayStats.topHeroes.orisa.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Orisa", R.drawable.orisa, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.pharah != null) {

                tmp = perfil.quickPlayStats.topHeroes.pharah.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Pharah", R.drawable.phara, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.reaper != null) {

                tmp = perfil.quickPlayStats.topHeroes.reaper.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Reaper", R.drawable.reaper, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.reinhardt != null) {

                tmp = perfil.quickPlayStats.topHeroes.reinhardt.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Reinhardt", R.drawable.reinhardt, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.roadhog != null) {

                tmp = perfil.quickPlayStats.topHeroes.roadhog.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Roadhog", R.drawable.roadhog, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.sigma != null) {

                tmp = perfil.quickPlayStats.topHeroes.sigma.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Sigma", R.drawable.sigma, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.soldier76 != null) {

                tmp = perfil.quickPlayStats.topHeroes.soldier76.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Soldado 76", R.drawable.soldado, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.sombra != null) {

                tmp = perfil.quickPlayStats.topHeroes.sombra.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Sombra", R.drawable.sombra, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.torbjorn != null) {

                tmp = perfil.quickPlayStats.topHeroes.torbjorn.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Torbjorn", R.drawable.torbjorn, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.tracer != null) {

                tmp = perfil.quickPlayStats.topHeroes.tracer.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Tracer", R.drawable.tracer, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.widowmaker != null) {

                tmp = perfil.quickPlayStats.topHeroes.widowmaker.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Widowmaker", R.drawable.widowmaker, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.winston != null) {

                tmp = perfil.quickPlayStats.topHeroes.winston.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Winston", R.drawable.winston, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.wreckingBall != null) {

                tmp = perfil.quickPlayStats.topHeroes.wreckingBall.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Wreckingball", R.drawable.hammond, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.zarya != null) {

                tmp = perfil.quickPlayStats.topHeroes.zarya.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Zarya", R.drawable.zarya, tiempo))
            }

            if (perfil.quickPlayStats.topHeroes.zenyatta != null) {

                tmp = perfil.quickPlayStats.topHeroes.zenyatta.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Zenyatta", R.drawable.zenyatta, tiempo))
            }

            //Ordenar tiempos de mayor a menor
            var sortedHeroes = heroes.sortedByDescending { it.tiempo }

            imgTop1Quick.setBackgroundResource(sortedHeroes[0].img)

            imgTop2Quick.setBackgroundResource(sortedHeroes[1].img)

            imgTop3Quick.setBackgroundResource(sortedHeroes[2].img)

            //Obtener tiempo jugado de heroes en rankeds

            if (perfil.competitiveStats!!.topHeroes.ana != null) {

                tmp = perfil.competitiveStats.topHeroes.ana!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Ana", R.drawable.ana, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.ashe != null) {

                tmp = perfil.competitiveStats.topHeroes.ashe!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Ashe", R.drawable.ashe, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.baptiste != null) {

                tmp = perfil.competitiveStats.topHeroes.baptiste!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Baptiste", R.drawable.baptiste, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.bastion != null) {

                tmp = perfil.competitiveStats.topHeroes.bastion!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Bastion", R.drawable.bastion, tiempo))
            }


            if (perfil.competitiveStats!!.topHeroes.brigitte != null) {

                tmp = perfil.competitiveStats.topHeroes.brigitte!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Brigitte", R.drawable.brigitte, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.dVa != null) {

                tmp = perfil.competitiveStats.topHeroes.dVa!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("DVa", R.drawable.dva, tiempo))
            }



            if (perfil.competitiveStats!!.topHeroes.doomfist != null) {
                tmp = perfil.competitiveStats.topHeroes.doomfist!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Doomfist", R.drawable.doomfist, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.echo != null) {

                tmp = perfil.competitiveStats!!.topHeroes.echo!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Echo", R.drawable.echo, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.genji != null) {

                tmp = perfil.competitiveStats.topHeroes.genji!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Genji", R.drawable.genji, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.hanzo != null) {

                tmp = perfil.competitiveStats.topHeroes.hanzo!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Hanzo", R.drawable.hanzo, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.junkrat != null) {

                tmp = perfil.competitiveStats.topHeroes.junkrat!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Junkrat", R.drawable.junkrat, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.lucio != null) {

                tmp = perfil.competitiveStats.topHeroes.lucio!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Lucio", R.drawable.lucio, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.mccree != null) {

                tmp = perfil.competitiveStats.topHeroes.mccree!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Mccree", R.drawable.mccree, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.mercy != null) {

                tmp = perfil.competitiveStats.topHeroes.mercy!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Mercy", R.drawable.mercy, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.moira != null) {

                tmp = perfil.competitiveStats.topHeroes.moira!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Moira", R.drawable.moira, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.orisa != null) {

                tmp = perfil.competitiveStats.topHeroes.orisa!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Orisa", R.drawable.orisa, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.pharah != null) {

                tmp = perfil.competitiveStats.topHeroes.pharah!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Pharah", R.drawable.phara, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.reaper != null) {

                tmp = perfil.competitiveStats.topHeroes.reaper!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Reaper", R.drawable.reaper, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.reinhardt != null) {

                tmp = perfil.competitiveStats.topHeroes.reinhardt!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Reinhardt", R.drawable.reinhardt, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.roadhog != null) {

                tmp = perfil.competitiveStats.topHeroes.roadhog!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Roadhog", R.drawable.roadhog, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.sigma != null) {

                tmp = perfil.competitiveStats.topHeroes.sigma!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Sigma", R.drawable.sigma, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.soldier76 != null) {

                tmp = perfil.competitiveStats.topHeroes.soldier76!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Soldado 76", R.drawable.soldado, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.sombra != null) {

                tmp = perfil.competitiveStats.topHeroes.sombra!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Sombra", R.drawable.sombra, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.torbjorn != null) {

                tmp = perfil.competitiveStats.topHeroes.torbjorn!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Torbjorn", R.drawable.torbjorn, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.tracer != null) {

                tmp = perfil.competitiveStats.topHeroes.tracer!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Tracer", R.drawable.tracer, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.widowmaker != null) {

                tmp = perfil.competitiveStats.topHeroes.widowmaker!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Widowmaker", R.drawable.widowmaker, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.winston != null) {

                tmp = perfil.competitiveStats.topHeroes.winston!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Winston", R.drawable.winston, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.wreckingBall != null) {

                tmp = perfil.competitiveStats.topHeroes.wreckingBall!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Wreckingball", R.drawable.hammond, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.zarya != null) {

                tmp = perfil.competitiveStats.topHeroes.zarya!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Zarya", R.drawable.zarya, tiempo))
            }

            if (perfil.competitiveStats!!.topHeroes.zenyatta != null) {

                tmp = perfil.competitiveStats.topHeroes.zenyatta!!.timePlayed!!.split(":")
                if (tmp.size == 3)
                    tiempo = (tmp[0].toInt() * 60) + tmp[1].toInt()
                else
                    tiempo = tmp[1].toInt()
                heroes.add(Heroe("Zenyatta", R.drawable.zenyatta, tiempo))
            }

            //Ordenar tiempos de mayor a menor
            sortedHeroes = heroes.sortedByDescending { it.tiempo }

            imgTop1Comp.setBackgroundResource(sortedHeroes[0].img)
            imgTop2Comp.setBackgroundResource(sortedHeroes[1].img)
            imgTop3Comp.setBackgroundResource(sortedHeroes[2].img)
        }

    }


}