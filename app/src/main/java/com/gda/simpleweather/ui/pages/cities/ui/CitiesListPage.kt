package com.gda.simpleweather.ui.pages.cities.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.PageCitiesListBinding
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.adapters.cities_list.CitiesListAdapter
import com.gda.simpleweather.ui.adapters.search.SearchAdapter
import com.gda.simpleweather.ui.base.BaseMvpFragment
import com.gda.simpleweather.ui.pages.cities.CitiesListPresenter
import com.gda.simpleweather.ui.pages.cities.CitiesListView
import com.jakewharton.rxbinding2.widget.RxTextView
import moxy.presenter.InjectPresenter
import java.util.concurrent.TimeUnit

class CitiesListPage : BaseMvpFragment(), CitiesListView, ICitiesList {

    @InjectPresenter
    lateinit var presenter: CitiesListPresenter

    lateinit var binding: PageCitiesListBinding

    private var adapter: CitiesListAdapter? = null
    private var searchAdapter: SearchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageCitiesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view.let {
                if (binding.searchResultsCard.visibility == View.GONE) {
                    requireActivity().finish()
                } else {
                    hideSearchElements()
                }
            }
        }
        binding.dimView.setOnClickListener {
            hideSearchElements()
        }
        initElements()
    }

    @SuppressLint("CheckResult")
    private fun initElements() {
        RxTextView.textChangeEvents(binding.searchInput)
            .skipInitialValue()
            .debounce(700, TimeUnit.MILLISECONDS)
            .subscribe {
                val text = binding.searchInput.text.trim()
                if (text.isNotBlank() && text.length > 2)
                    presenter.search(text.toString())
            }

    }

    override fun setList(list: List<WeatherViewItem?>, chosen: String) {
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
                .navigate(R.id.action_citiesListPage_to_weatherDetailsPage, args)
        }
    }

    override fun setSearchResults(results: List<String>) {
        if (searchAdapter == null || binding.searchResults.adapter == null) {
            searchAdapter = SearchAdapter(this)
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.searchResults.adapter = searchAdapter
            binding.searchResults.layoutManager = manager
        }
        searchAdapter?.setResults(results)
        binding.searchResultsCard.visibility = View.VISIBLE
        binding.dimView.visibility = View.VISIBLE
    }

    override fun onCityClick(cityName: String) {
        presenter.setChosen(cityName)
    }

    override fun onSearchItemClick(text: String) {
        hideSearchElements()
        presenter.getWeather(text)
    }

    private fun hideSearchElements() {
        binding.dimView.visibility = View.INVISIBLE
        binding.searchResultsCard.visibility = View.GONE
    }

}