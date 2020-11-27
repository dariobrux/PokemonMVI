package com.dariobrux.pokemon.ui.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.common.animateCardBackgroundColor
import com.dariobrux.pokemon.common.getColorCompat
import com.dariobrux.pokemon.common.loadImage
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.databinding.ItemListBinding
import java.util.*

class PokemonAdapter(private val context: Context) : PagingDataAdapter<PokemonEntity, PokemonAdapter.PokemonViewHolder>(PokemonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class PokemonViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonEntity) = with(binding) {
            txt.text = item.name.capitalize(Locale.getDefault())
            img.loadImage(context, item.image) {
                card.setCardBackgroundColor(it)
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