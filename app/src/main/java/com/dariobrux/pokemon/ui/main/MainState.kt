package com.dariobrux.pokemon.ui.main

import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow

data class MainState(val pagingData: Flow<PagingData<PokemonEntity>>?) : UIState()