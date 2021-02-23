package com.gda.simpleweather.ui.adapters.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.databinding.ItemSearchBinding
import com.gda.simpleweather.ui.pages.cities.ui.ICitiesList

class SearchAdapter(private val callback: ICitiesList) : RecyclerView.Adapter<SearchViewHolder>() {

    lateinit var items: List<String>

    fun setResults(results : List<String>){
        this.items = results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
        holder.showDivider(position > 0)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}