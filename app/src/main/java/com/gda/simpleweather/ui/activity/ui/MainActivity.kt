package com.gda.simpleweather.ui.activity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.ActivityMainBinding
import com.gda.simpleweather.ui.activity.MainPresenter
import com.gda.simpleweather.ui.activity.MainView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), IMain, MainView {

    private lateinit var binding: ActivityMainBinding

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun setupNavigation(defaultCity: String?) {
        val host =
            supportFragmentManager.findFragmentById(R.id.weather_host_fragment) as NavHostFragment
        val inflater = host.navController.navInflater
        val navController = host.navController
        if (defaultCity.isNullOrBlank()) {
            val graph = inflater.inflate(R.navigation.nav_graph);
            graph.startDestination = R.id.citiesListPage
            navController.graph = inflater.inflate(R.navigation.nav_graph)
        } else {
            val args = Bundle()
            args.putString("name", defaultCity)
            val graph = inflater.inflate(R.navigation.nav_graph);
            graph.startDestination = R.id.weatherDetailsPage
            navController.setGraph(graph, args)
        }
    }

    override fun onError(error: String?) {
        Toast.makeText(this, error ?: "Что-то пошло не так", Toast.LENGTH_SHORT).show()
    }
}