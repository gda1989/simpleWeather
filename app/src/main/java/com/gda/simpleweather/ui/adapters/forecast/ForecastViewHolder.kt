package com.gda.simpleweather.ui.adapters.forecast

import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.databinding.ItemForecastBinding
import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.ui.utils.Renderer

class ForecastViewHolder(private val binding: ItemForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(info: ForecastItem) {
        info.iconCode?.let { binding.icon.setImageResource(Renderer.renderIcon(it)) }
        info.time?.let {
            binding.date.text = it.toString()
        }
        info.pressure?.let { binding.pressure.text = it.toString() }
        info.temp?.let { binding.temperature.text = it.toString() }
        info.wind?.let { binding.wind.text = it }
    }

}