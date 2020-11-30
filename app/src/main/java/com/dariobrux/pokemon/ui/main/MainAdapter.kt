package com.dariobrux.pokemon.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.pokemon.common.extension.loadImage
import com.dariobrux.pokemon.common.extension.withAlpha
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.databinding.PokemonItemListBinding
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 27/11/2020.
 *
 * This adapter displays the Pokemon.
 *
 */
class MainAdapter(private val context: Context, private val listener: OnItemSelectedListener?) : PagingDataAdapter<PokemonEntity, MainAdapter.PokemonViewHolder>(PokemonComparator) {

    interface OnItemSelectedListener {
        fun onItemSelected(pokemonEntity: PokemonEntity, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(PokemonItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class PokemonViewHolder(private val binding: PokemonItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonEntity) = with(binding) {
            card.transitionName = item.name
            txt.text = item.name.capitalize(Locale.getDefault())
            img.loadImage(context, item.images.first().url) {
                card.setCardBackgroundColor(it.withAlpha(220))
            }
            card.setOnClickListener {
                listener?.onItemSelected(item, it)
            }
        }
    }

    object PokemonComparator : DiffUtil.ItemCallback<PokemonEntity>() {
        override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val TAG = "PokemonAdapter"
    }
}