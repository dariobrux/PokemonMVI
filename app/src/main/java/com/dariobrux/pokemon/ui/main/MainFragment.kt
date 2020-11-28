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
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.databinding.FragmentMainBinding
import com.dariobrux.pokemon.ui.util.PokemonSpaceItemDecoration
import com.google.android.material.card.MaterialCardView
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIError
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), MainAdapter.OnItemSelectedListener {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mainAdapter: MainAdapter

    // Binding
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainAdapter = MainAdapter(context, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onStates(viewModel) { state ->
            when (state) {
                is UIState.Loading -> showIsLoading()
                is MainState -> showIsLoaded(state)
                is UIState.Failed -> showError(state.error)
            }
        }
        viewModel.getPokemonList(0, 1117)

        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.addItemDecoration(PokemonSpaceItemDecoration(requireContext().resources.getDimensionPixelSize(R.dimen.regular_padding)))
        binding.recycler.adapter = mainAdapter

//        lifecycleScope.launch {
//            viewModel.getPokemonList(0, 0)
//                .collectLatest { pagedData ->
//                pokemonAdapter.submitData(pagedData)
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(error: UIError?) {
        // TODO("Not yet implemented")
    }

    private fun showIsLoading() {
//        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.infinite_blinking_animation)
//        binding.imgSplash.startAnimation(animation)
    }

    private fun showIsLoaded(state: MainState) {
        state.pagingData?.let {
            lifecycleScope.launch {
                it.collectLatest {
                    mainAdapter.submitData(it)
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