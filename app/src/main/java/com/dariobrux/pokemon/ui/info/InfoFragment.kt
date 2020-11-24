package com.dariobrux.pokemon.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : Fragment() {

    private val viewModel: InfoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }
}