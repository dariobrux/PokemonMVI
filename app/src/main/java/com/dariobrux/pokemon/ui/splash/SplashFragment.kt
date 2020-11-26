package com.dariobrux.pokemon.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.databinding.FragmentSplashBinding
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIError
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    // Binding
    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imgSplash.scaleX = 0f
        binding.imgSplash.scaleY = 0f
        binding.imgSplash.alpha = 0.3f
        binding.imgSplash.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.75f).setDuration(1000).setInterpolator(AnticipateOvershootInterpolator()).withEndAction {
            binding.imgSplash.animate().rotation(360f).scaleX(1f).scaleY(1f).alpha(1f).setDuration(2000).setInterpolator(BounceInterpolator()).start()
        }.start()
    }

    override fun onResume() {
        super.onResume()

        activityScope.launch {
            delay(3500)
            NavHostFragment.findNavController(this@SplashFragment).navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
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
}