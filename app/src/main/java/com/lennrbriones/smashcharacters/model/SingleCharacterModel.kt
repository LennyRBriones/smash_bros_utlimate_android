package com.lennrbriones.smashcharacters.model


    data class SingleCharacterModel(
        val name: String,
        val series: Series,
        val fighterNumber: String,
        val images: Images,
        val dlcCharacter: Boolean,
        val description: String,
        val otherAppearances: List<String>? = null
    )

    data class Series(
        val name: String,
        val image: String
    )

    data class Images(
        val bannerImage: String,
        val fullImage: String
    )

