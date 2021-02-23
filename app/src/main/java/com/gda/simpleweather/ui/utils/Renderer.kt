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
                    in 158..220 -> "южный"
                    in 221..247 -> "юго-западный"
                    in 248..292 -> "западный"
                    in 293..337 -> "cеверо-западный"
                    in 338..360 -> "северный"
                    in 0..22 -> "северный"
                    in 32..67 -> "северо-восточный"
                    in 68..112 -> "восточный"
                    in 113..157 -> "юго-восточный"
                    else -> "??"
                }
                return "ветер $windDirect, ${windSpeed.toInt()}м/с"
            }
        } else {
            return ""
        }
    }

}