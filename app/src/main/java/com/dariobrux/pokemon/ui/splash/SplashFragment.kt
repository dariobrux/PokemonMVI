package com.dariobrux.pokemon.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.databinding.FragmentSplashBinding
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIError
import io.uniflow.core.flow.data.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()

    // Binding
    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
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

    private fun showIsLoading() {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.infinite_blinking_animation)
        binding.imgSplash.startAnimation(animation)
    }

    private fun showIsLoaded() {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_mainFragment)
    }

    private fun showError(error: UIError?) {
//        splashIcon.visibility = View.GONE
//        splashIconFail.visibility = View.VISIBLE
//        Snackbar.make(splash, "SplashActivity got error : $error", Snackbar.LENGTH_INDEFINITE)
//            .setAction(R.string.retry) {
//                splashViewModel.getLastWeather()
//            }
//            .show()
    }
}