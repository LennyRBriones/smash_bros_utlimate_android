package com.lennrbriones.smashcharacters.state

data class CharacterState(
    val name: String = "",
    val seriesName: String = "",
    val seriesImage: String = "",
    val fighterNumber: String = "",
    val bannerImage: String = "",
    val fullImage: String = "",
    val dlcCharacter: Boolean = false,
    val description: String = "",
    val otherAppearances: List<String> = emptyList()
)