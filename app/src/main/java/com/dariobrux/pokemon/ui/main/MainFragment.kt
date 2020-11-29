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

    private var binding: FragmentMainBinding? = null

    private var mainAdapter : MainAdapter? = null

//    private var totalScroll = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        retainInstance = true
        mainAdapter = MainAdapter(requireContext(), this)//.apply {
//            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
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

        binding?.let {
            it.recycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            it.recycler.addItemDecoration(PokemonSpaceItemDecoration(requireContext().resources.getDimensionPixelSize(R.dimen.regular_padding)))
            it.recycler.adapter = mainAdapter
//            it.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    totalScroll += dy
//                }
//            })
//            it.recycler.postDelayed( {
//                it.recycler.layoutManager!!.scrollToPosition(45)
//            }, 2000)
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
//        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.infinite_blinking_animation)
//        binding.imgSplash.startAnimation(animation)
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