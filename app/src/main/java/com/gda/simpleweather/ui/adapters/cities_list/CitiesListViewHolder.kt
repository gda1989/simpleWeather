package com.gda.simpleweather.ui.adapters.cities_list

import android.annotation.SuppressLint
import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.ItemCitiesListBinding
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.pages.cities.ui.ICitiesList
import com.gda.simpleweather.ui.utils.Renderer

class CitiesListViewHolder(
    private val binding: ItemCitiesListBinding,
    private val callback: ICitiesList
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
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

        infoModel.main?.temp?.let { binding.temperature.text = "${if (it > 273) "+" else ""}${(it - 273).toInt()} ${
            itemView.context.resources.getString(
                R.string.deg_symbol
            )
        }C" }

        infoModel.weather?.get(0)?.icon?.let { binding.icon.setImageResource(Renderer.renderIcon(it)) }

    }

    fun showDivider(show: Boolean) {
        binding.divider.visibility = if (show) View.VISIBLE else View.GONE
    }

}