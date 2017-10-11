package com.joaquin.toptierlabs.targetmvd.ui.components.custom

import android.view.ViewManager
import android.widget.RelativeLayout
import com.joaquin.toptierlabs.targetmvd.R
import org.jetbrains.anko.*

fun ViewManager.customLoading(): RelativeLayout {
    return relativeLayout {
        backgroundResource = R.drawable.background_progress
        padding = dip(30)

        progressBar(R.style.LoadingSpinner) {}.lparams {
            width = wrapContent
            height = wrapContent
        }
    }
}