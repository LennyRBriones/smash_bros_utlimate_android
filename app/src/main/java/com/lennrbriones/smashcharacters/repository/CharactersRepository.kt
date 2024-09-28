package com.lennrbriones.smashcharacters.repository

import com.lennrbriones.smashcharacters.data.ApiSmash
import com.lennrbriones.smashcharacters.model.CharactersModel
import com.lennrbriones.smashcharacters.model.SingleCharacterModel
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val apiSmash: ApiSmash) {

    suspend fun getCharacters(): List<CharactersModel>? {
        val response = apiSmash.getCharacters()
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun getCharacterByNumber(id: String): SingleCharacterModel? {
        val response = apiSmash.getCharacterByNumber(id)
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }
}