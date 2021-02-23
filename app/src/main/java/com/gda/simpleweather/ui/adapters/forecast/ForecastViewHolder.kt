package com.gda.simpleweather.ui.adapters.forecast

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.ItemForecastBinding
import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.ui.utils.Renderer
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ForecastViewHolder(private val binding: ItemForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(info: ForecastItem) {
        info.iconCode?.let { binding.icon.setImageResource(Renderer.renderIcon(it)) }

        var time = ""
        var pressure = ""
        var temperature = ""
        var wind = ""

        info.time?.let {
            time = try {
                val calendar = Calendar.getInstance()
                calendar.time = Date(it * 1000)
                val format = SimpleDateFormat("dd MMM HH:mm")
                format.format(calendar.time)
            } catch (e: ParseException) {
                it.toString()
            }
        }
        info.pressure?.let { pressure = "${(it * 0.75).toInt()} мм.рт.ст" }
        info.temp?.let {
            temperature =
                "${if (it > 273) "+" else ""}${(it - 273).toInt()} ${
                    itemView.context.resources.getString(
                        R.string.deg_symbol
                    )
                }C"
        }
        info.wind?.let { wind = it }
        binding.weatherString.text =
            "$time : $temperature\n$wind\nатмосферное давление $pressure"
    }

    fun showDivider(show: Boolean) {
        binding.divider.visibility = if (show) View.VISIBLE else View.GONE
    }

}