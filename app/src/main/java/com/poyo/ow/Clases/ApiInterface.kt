package com.poyo.ow.Clases

import com.poyo.ow.Models.Perfil
import com.poyo.ow.Models.Profile
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET
    fun verificarPerfil(
            @Url  url: String,
    ): Call<Profile?>?
}