package com.gda.simpleweather.ui.base

import android.widget.Toast
import moxy.MvpAppCompatFragment

open class BaseMvpFragment : MvpAppCompatFragment(), BaseMvpView {

    override fun onError(error: String?) {
        Toast.makeText(context, error ?: "Что-то пошло не так", Toast.LENGTH_SHORT).show()
    }


}