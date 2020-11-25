package com.dariobrux.pokemon.ui.splash

import com.dariobrux.pokemon.domain.usecase.GetPokemonList
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

class SplashViewModel(private val pokemonList: GetPokemonList) : AndroidDataFlow() {

    fun getPokemonList(offset: Int, limit: Int) = action(
        onAction = {
            sendEvent { UIEvent.Loading }
            pokemonList(offset, limit)
            setState { UIState.Success }
        },
        onError = { error, _ -> setState { UIState.Failed("getPokemon failed", error) } })
}