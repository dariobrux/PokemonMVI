package com.dariobrux.pokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dariobrux.pokemon.R

/**
 *
 * Created by Dario Bruzzese on 28/10/2020.
 *
 * This is the only activity project
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}