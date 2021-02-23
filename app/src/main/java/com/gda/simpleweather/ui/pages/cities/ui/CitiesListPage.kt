package com.gda.simpleweather.ui.pages.cities.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.PageCitiesListBinding
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.adapters.cities_list.CitiesListAdapter
import com.gda.simpleweather.ui.base.BaseMvpFragment
import com.gda.simpleweather.ui.pages.cities.CitiesListPresenter
import com.gda.simpleweather.ui.pages.cities.CitiesListView
import moxy.presenter.InjectPresenter

class CitiesListPage : BaseMvpFragment(), CitiesListView, ICitiesList {

    @InjectPresenter
    lateinit var presenter: CitiesListPresenter

    lateinit var binding: PageCitiesListBinding

    private var adapter: CitiesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageCitiesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setList(list: List<WeatherViewItem?>, chosen : String) {
        if (adapter == null || binding.citiesList.adapter == null) {
            adapter = CitiesListAdapter(this)
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.citiesList.adapter = adapter
            binding.citiesList.layoutManager = manager
        }
        adapter?.updateList(list, chosen)

    }

    override fun showDetailsPage(chosen: String) {
        this.view?.let { it1 ->
            val args = Bundle()
            args.putString("name", chosen)
            Navigation.findNavController(it1)
                .navigate(R.id.weatherDetailsPage, args)
        }
    }

    override fun onCityClick(cityName: String) {
        presenter.setChosen(cityName)
    }

}