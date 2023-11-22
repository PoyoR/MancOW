package com.poyo.ow.Clases

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{
        val BASE_URL = "https://ovrstat.com"
        private var retrofit: Retrofit? = null

        /**
         * This method returns retrofit client instance
         *
         * @return Retrofit object
         */
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}