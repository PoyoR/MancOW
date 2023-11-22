package com.poyo.ow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.poyo.ow.Perfil.View.PerfilFragment
import com.poyo.ow.Ruleta.View.RuletaFragment
import kotlinx.android.synthetic.main.activity_inicio.*

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        showSelectedFragment(PerfilFragment())

        bottomBar.onItemSelected = { it ->
            Log.i("INICIO", "Item $it selected")
            if (it == 0)
                showSelectedFragment(PerfilFragment())
            else if (it == 1)
                showSelectedFragment(HeroesFragment())
            else if (it == 2)
                showSelectedFragment(RuletaFragment())
        }
    }

    private fun showSelectedFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}