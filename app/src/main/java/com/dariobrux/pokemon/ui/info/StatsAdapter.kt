package com.dariobrux.pokemon.ui.info

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.pokemon.common.StatsMapper
import com.dariobrux.pokemon.common.TypeMapper
import com.dariobrux.pokemon.common.loadImage
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.dariobrux.pokemon.databinding.StatsItemListBinding
import com.dariobrux.pokemon.databinding.TypeItemListBinding
import java.util.*

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