package com.gda.simpleweather.ui.adapters.cities_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.databinding.ItemCitiesListBinding
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.pages.cities.ui.ICitiesList

class CitiesListAdapter(private val callback: ICitiesList) :
    RecyclerView.Adapter<CitiesListViewHolder>() {

    lateinit var items: List<WeatherViewItem?>
    private var chosen : String = ""

    fun updateList(items: List<WeatherViewItem?>, chosen : String) {
        this.items = items
        this.chosen = chosen
        notifyDataSetChanged()      // todo: diffUtil
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesListViewHolder {
        val binding =
            ItemCitiesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitiesListViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: CitiesListViewHolder, position: Int) {
        items[position]?.let { holder.bind(it, chosen == it.cityName) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}