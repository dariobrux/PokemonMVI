package com.dariobrux.pokemon.ui.info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.pokemon.common.mapper.StatsMapper
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.databinding.StatsItemListBinding
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 28/11/2020.
 *
 * This adapter displays the Pokemon stats items.
 *
 */
class StatsAdapter(private val context: Context, private val items: List<StatsEntity>) : RecyclerView.Adapter<StatsAdapter.TypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        return TypeViewHolder(StatsItemListBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TypeViewHolder(private val binding: StatsItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StatsEntity) = with(binding) {
            val statsPair = StatsMapper.getStatsAbbreviationColor(item.name)
            txtLabel.text = statsPair.first.toUpperCase(Locale.getDefault())

            progress.let {
                it.progressColor = statsPair.second
                it.progress = item.value.toFloat()
                it.progressText = item.value.toString()
            }
        }
    }
}