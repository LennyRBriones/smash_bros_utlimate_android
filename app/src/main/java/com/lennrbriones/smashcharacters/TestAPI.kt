package com.lennrbriones.smashcharacters

import com.lennrbriones.smashcharacters.data.ApiSmash
import com.lennrbriones.smashcharacters.repository.CharactersRepository
import com.lennrbriones.smashcharacters.utils.Constants.Companion.BASE_URL
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() = runBlocking {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiSmash = retrofit.create(ApiSmash::class.java)
    val repository = CharactersRepository(apiSmash)

    try {
        val characters = repository.getCharacters()
        if (characters != null) {
            println("Characters fetched successfully:")
            characters.forEach { character ->
                println("Name: ${character.name}, Fighter Number: ${character.fighterNumber}")
            }
        } else {
            println("Failed to fetch characters.")
        }
    } catch (e: Exception) {
        println("Error fetching characters: ${e.message}")
    }

    try {
        val characterId = "03"
        val character = repository.getCharacterByNumber(characterId)
        if (character != null) {
            println("Character fetched successfully:")
            println("Name: ${character.name}, Description: ${character.description}")
        } else {
            println("Character with ID $characterId not found.")
        }
    } catch (e: Exception) {
        println("Error fetching character: ${e.message}")
    }
}