package com.lennrbriones.smashcharacters.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lennrbriones.smashcharacters.model.CharactersModel
import com.lennrbriones.smashcharacters.repository.CharactersRepository
import com.lennrbriones.smashcharacters.state.CharacterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(private val repo: CharactersRepository) :
    ViewModel() {

    private val _characters = MutableStateFlow<List<CharactersModel>>(emptyList())
    val characters = _characters.asStateFlow()


    var state by mutableStateOf(CharacterState())
        private set

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLoadingCharacter = MutableStateFlow(true)
    val isLoadingCharacter = _isLoading.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                val response = repo.getCharacters()
                val sortedCharacters = response?.sortedBy {
                    it.fighterNumber.extractNumber()
                } ?: emptyList()
                _characters.value = sortedCharacters
            }
            _isLoading.value = false
        }
    }

    private fun String.extractNumber(): Int {
        val number = Regex("\\d+").find(this)?.value
        return number?.toIntOrNull() ?: Int.MAX_VALUE
    }

    fun getCharacterByNumber(id: String) {
        viewModelScope.launch {
            _isLoadingCharacter.value = true
            withContext(Dispatchers.IO) {
                val response = repo.getCharacterByNumber(id)
                state = state.copy(
                    name = response?.name ?: "",
                    seriesName = response?.series?.name ?: "",
                    seriesImage = response?.series?.image ?: "",
                    fighterNumber = response?.fighterNumber ?: "",
                    bannerImage = response?.images?.bannerImage ?: "",
                    fullImage = response?.images?.fullImage ?: "",
                    dlcCharacter = response?.dlcCharacter ?: false,
                    description = response?.description ?: "",
                    otherAppearances = response?.otherAppearances ?: emptyList()
                )
            }
            _isLoadingCharacter.value = false
        }
    }

    fun cleanScreen() {
        state = state.copy(
            name = "",
            seriesName = "",
            seriesImage = "",
            fighterNumber = "",
            bannerImage = "",
            fullImage = "",
            dlcCharacter = false,
            description = "",
            otherAppearances = emptyList()
        )
    }
}