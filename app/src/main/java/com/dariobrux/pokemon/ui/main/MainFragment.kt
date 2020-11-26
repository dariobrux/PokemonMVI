package com.dariobrux.pokemon.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.databinding.FragmentMainBinding
import com.dariobrux.pokemon.databinding.FragmentSplashBinding
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIError
import io.uniflow.core.flow.data.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    // Binding
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onStates(viewModel) { state ->
            when (state) {
                is UIState.Loading -> showIsLoading()
                is UIState.Success -> showIsLoaded()
                is UIState.Failed -> showError(state.error)
            }
        }
        viewModel.getPokemonList(0, 1117)
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

    private fun showIsLoaded() {
//        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_mainFragment)
    }
}