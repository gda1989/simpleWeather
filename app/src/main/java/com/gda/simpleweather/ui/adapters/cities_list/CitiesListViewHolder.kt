package com.gda.simpleweather.ui.adapters.cities_list

import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.databinding.ItemCitiesListBinding
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.pages.cities.ui.ICitiesList

class CitiesListViewHolder(
    private val binding: ItemCitiesListBinding,
    private val callback: ICitiesList
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(infoModel: WeatherViewItem, isChosen: Boolean) {
        binding.radioButton.setOnCheckedChangeListener{ _: CompoundButton, _: Boolean -> }
        binding.radioButton.isChecked = isChosen
        binding.radioButton.setOnCheckedChangeListener{ _: CompoundButton, _: Boolean -> infoModel.cityName?.let {
            callback.onCityClick(
                it
            )
        } }
        binding.cityName.text = infoModel.cityName
        binding.item.setOnClickListener { infoModel.cityName?.let { it1 -> callback.onCityClick(it1) } }
    }

}