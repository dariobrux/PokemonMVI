package com.dariobrux.pokemon.ui.main

import com.dariobrux.pokemon.domain.usecase.GetPokemonList
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

class MainViewModel(private val pokemonList: GetPokemonList) : AndroidDataFlow() {

    fun getPokemonList(offset: Int, limit: Int) = action(
        onAction = {
            sendEvent { UIEvent.Loading }
            val mainState = MainState(pokemonList(offset, limit))
            setState { mainState }
        },
        onError = { error, _ -> setState { UIState.Failed("getPokemon failed", error) } })
}