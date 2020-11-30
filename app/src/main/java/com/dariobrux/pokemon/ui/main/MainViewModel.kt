package com.dariobrux.pokemon.ui.main

import com.dariobrux.pokemon.domain.usecase.GetPokemonList
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 */
class MainViewModel(private val pokemonList: GetPokemonList) : AndroidDataFlow() {

    var state = pokemonList.state

    fun getPokemonList() = action(
        onAction = {
            sendEvent { UIEvent.Loading }
            val mainState = MainState(pokemonList())
            setState { mainState }
        },
        onError = { error, _ -> setState { UIState.Failed("getPokemon failed", error) } })
}