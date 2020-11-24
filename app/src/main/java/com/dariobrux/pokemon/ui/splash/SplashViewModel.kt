package com.dariobrux.pokemon.ui.splash

import com.dariobrux.pokemon.domain.usecase.pokemon.LoadPokemonList
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

class SplashViewModel(private val loadPokemonList: LoadPokemonList) : AndroidDataFlow() {

    fun getPokemonList() = action(
        onAction = {
            sendEvent { UIEvent.Loading }
            loadPokemonList()
            setState { UIState.Success }
        },
        onError = { error, _ -> setState { UIState.Failed("getPokemon failed", error) } })
}