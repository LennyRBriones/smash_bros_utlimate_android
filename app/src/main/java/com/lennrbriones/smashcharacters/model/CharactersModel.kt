package com.lennrbriones.smashcharacters.model


data class CharactersModel(
    val name: String,
    val fighterNumber: String,
    val images: CharacterImages,
    val series: SeriesData,
)

data class CharacterImages(
    val bannerImage: String,
    val fullImage: String
)

data class SeriesData(
    val name: String,
    val image: String
)