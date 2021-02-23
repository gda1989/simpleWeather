package com.gda.simpleweather.ui.pages.weather_details.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.BottomForecastBinding
import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.ui.adapters.forecast.ForecastAdapter
import com.gda.simpleweather.ui.base.BaseBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback

class ForecastBottomPage : BaseBottomSheetDialogFragment() {

    private lateinit var binding: BottomForecastBinding

    private var adapter: ForecastAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomForecastBinding.inflate(inflater, container, false)
        setDimBackground()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.let { it ->
            val view = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            context?.let {
                view.layoutParams.height = (it.resources.displayMetrics.heightPixels * 0.6).toInt()
            }

            val bottomSheetBehaviour: BottomSheetBehavior<View> = BottomSheetBehavior.from(view)

            bottomSheetBehaviour.peekHeight =
                ((resources.getDimension(R.dimen.forecast_item_height)).toInt())
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetBehaviour.isHideable = false
            isCancelable = false
            bottomSheetBehaviour.addBottomSheetCallback(object : BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (
                        newState == BottomSheetBehavior.STATE_HIDDEN
                    )
                        bottomSheetBehaviour.state =
                            BottomSheetBehavior.STATE_COLLAPSED
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    (requireParentFragment() as IWeatherDetails).setProgress((slideOffset * 1.5).toFloat())
                }
            })
        }
    }

    fun setForecast(list: List<ForecastItem?>) {
        if (adapter == null || binding.forecastList.adapter == null) {
            adapter = ForecastAdapter()
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.forecastList.adapter = adapter
            binding.forecastList.layoutManager = manager
        }
        adapter?.updateList(list)
    }

    private fun setDimBackground() {
        dialog?.let { dialog ->
            dialog.window?.let { window ->
                context?.let { context ->
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    window.setBackgroundDrawable(
                        ColorDrawable(
                            ContextCompat.getColor(context, R.color.transparent)
                        )
                    )
                    setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)

                    window.setDimAmount(0f)
                }
            }
        }
    }

}