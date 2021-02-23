package com.gda.simpleweather.ui.utils

import com.gda.simpleweather.R

object Renderer {

    fun renderIcon(iconCode: String): Int {
        return when (iconCode) {
            "01d" -> R.drawable.i01d
            "01n" -> R.drawable.i01d
            "02d" -> R.drawable.i02d
            "02n" -> R.drawable.i02n
            "03d" -> R.drawable.i03d
            "03n" -> R.drawable.i03n
            "04d" -> R.drawable.i04d
            "04n" -> R.drawable.i04n
            "09d" -> R.drawable.i09d
            "09n" -> R.drawable.i09n
            "10d" -> R.drawable.i10d
            "10n" -> R.drawable.i10n
            "11d" -> R.drawable.i11d
            "11n" -> R.drawable.i11n
            "13d" -> R.drawable.i13d
            "13n" -> R.drawable.i13n
            "50d" -> R.drawable.i50d
            "50n" -> R.drawable.i50n
            else -> R.drawable.i01d
        }
    }

    fun renderWind(windAzimuth: Int?, windSpeed: Double?): String {
        if (windSpeed != null) {
            if (windSpeed <= 1) {
                return "штиль"
            } else {
                val windDirect = when (windAzimuth) {
                    in 158..220 -> "ю"
                    in 221..247 -> "ю/з"
                    in 248..292 -> "з"
                    in 293..337 -> "c/з"
                    in 338..360 -> "с"
                    in 0..22 -> "с"
                    in 32..67 -> "с/в"
                    in 68..112 -> "в"
                    in 113..157 -> "ю/в"
                    else -> "??"
                }
                return "$windDirect, ${windSpeed.toInt()}м/с"
            }
        } else {
            return ""
        }
    }

}