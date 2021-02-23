package com.gda.simpleweather.ui.adapters.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.databinding.ItemSearchBinding
import com.gda.simpleweather.ui.pages.cities.ui.ICitiesList

class SearchViewHolder(private val binding: ItemSearchBinding, private val callback: ICitiesList) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.resultText.text = text
        binding.item.setOnClickListener { callback.onSearchItemClick(text) }
    }

    fun showDivider(show: Boolean) {
        binding.divider.visibility = if (show) View.VISIBLE else View.GONE
    }

}