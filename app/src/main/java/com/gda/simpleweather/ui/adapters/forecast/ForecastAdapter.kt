package com.gda.simpleweather.ui.adapters.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.databinding.ItemForecastBinding
import com.gda.simpleweather.interactors.items.ForecastItem

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    private var list: List<ForecastItem?>? = null

    fun updateList(list: List<ForecastItem?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding =
            ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        list?.get(position)?.let {
            holder.bind(it)
            holder.showDivider(position > 0)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}