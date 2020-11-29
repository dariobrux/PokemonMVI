package com.dariobrux.pokemon.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.common.extension.toGone
import com.dariobrux.pokemon.common.extension.toVisible
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.repository.PokemonRepository
import com.dariobrux.pokemon.databinding.FragmentMainBinding
import com.dariobrux.pokemon.ui.util.PokemonSpaceItemDecoration
import com.google.android.material.card.MaterialCardView
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIError
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment : Fragment(), MainAdapter.OnItemSelectedListener {

    private val viewModel: MainViewModel by viewModel()

    private var binding: FragmentMainBinding? = null

    private var mainAdapter: MainAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainAdapter = MainAdapter(requireContext(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onStates(viewModel) { state ->
            when (state) {
                is MainState -> showIsLoaded(state)
                is UIState.Failed -> showError(state.error)
            }
        }
        viewModel.getPokemonList(0, 1117)

        binding?.let {
            it.recycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            it.recycler.addItemDecoration(PokemonSpaceItemDecoration(requireContext().resources.getDimensionPixelSize(R.dimen.regular_padding)))
            it.recycler.adapter = mainAdapter
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                PokemonRepository.State.LOADING -> {
                    showLoading()
                }
                else -> {
                    hideLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding?.let { bind ->
            bind.pikachu.toVisible()
            rotatePikachu()

            bind.loading.toVisible()
            bind.mask.toVisible()
        }
    }

    private fun rotatePikachu() {
        binding?.pikachu?.animate()?.rotationYBy(180f)?.setDuration(0)?.setStartDelay(500)?.withEndAction {
            rotatePikachu()
        }?.start()
    }

    private fun hideLoading() {
        binding?.let { bind ->
            bind.pikachu.toGone()
            bind.loading.toGone()
            bind.mask.toGone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.recycler?.adapter = null
        binding = null
    }

    private fun showError(error: UIError?) {
        // TODO("Not yet implemented")
    }

    private fun showIsLoading() {

    }

    private fun showIsLoaded(state: MainState) {
        state.pagingData?.let {
            lifecycleScope.launch {
                it.collectLatest {
                    mainAdapter?.submitData(it)
                }
            }
        }
    }

    override fun onItemSelected(pokemonEntity: PokemonEntity, view: View) {
        val extras = FragmentNavigatorExtras((view as MaterialCardView) to pokemonEntity.name)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_mainFragment_to_infoFragment, Bundle().apply {
                putSerializable("pokemon", pokemonEntity)
            },
            null,
            extras
        )
    }
}