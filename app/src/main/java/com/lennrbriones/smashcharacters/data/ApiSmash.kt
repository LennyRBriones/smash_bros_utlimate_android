package com.lennrbriones.smashcharacters.data

import com.lennrbriones.smashcharacters.model.CharactersModel
import com.lennrbriones.smashcharacters.model.ListModel
import com.lennrbriones.smashcharacters.model.SingleCharacterModel
import com.lennrbriones.smashcharacters.utils.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiSmash {

    @GET(ENDPOINT)
    suspend fun getCharacters(): Response<List<CharactersModel>>


    @GET("$ENDPOINT/{id}")
    suspend fun getCharacterByNumber(@Path(value = "id")id: String): Response<SingleCharacterModel>
}