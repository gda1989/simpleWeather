package com.gda.simpleweather.ui.base

import android.app.Dialog
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    //спасибо медиуму)
    override fun setCancelable(cancelable: Boolean) {
        val dialog: Dialog? = dialog
        dialog?.let { it ->
            val touchOutsideView: View? = it.window?.decorView
                ?.findViewById(com.google.android.material.R.id.touch_outside)
            val bottomSheetView: View? = it.window?.decorView
                ?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            if (cancelable) {
                bottomSheetView?.let { BottomSheetBehavior.from<View>(it).setHideable(true) }
            } else {
                touchOutsideView?.setOnClickListener(null)
                bottomSheetView?.let { BottomSheetBehavior.from<View>(it).setHideable(false) }
            }
        }

    }

}