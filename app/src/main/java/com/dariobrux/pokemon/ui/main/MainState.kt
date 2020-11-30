package com.dariobrux.pokemon.ui.main

import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow

/**
 *
 * Created by Dario Bruzzese on 27/11/2020.
 *
 * This state is used by Data Flow to pass info to the fragment.
 *
 */
data class MainState(val pagingData: Flow<PagingData<PokemonEntity>>?) : UIState()