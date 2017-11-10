package com.joaquin.toptierlabs.targetmvd.ui.components.custom

import android.view.Gravity
import android.view.View
import android.view.ViewManager
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.utils.color
import org.jetbrains.anko.*

fun ViewManager.customAlert(titleRes: Int, messageRes: Int, positiveButtonRes: Int): View {
    return verticalLayout {
        textView {
            textResource = titleRes
            textColor = color(R.color.colorSecondary)
            textSize = 20f
        }.lparams {
            width = wrapContent
            height = wrapContent
            gravity = Gravity.START
            leftMargin = dip(20)
            topMargin = dip(10)
        }
        textView {
            textResource = messageRes
            textColor = color(R.color.defaultTextColor)
            textSize = 14f
        }.lparams {
            width = wrapContent
            height = wrapContent
            gravity = Gravity.START
            leftMargin = dip(20)
            topMargin = dip(20)
        }
        textView {
            textResource = positiveButtonRes
            textColor = color(R.color.colorSecondary)
            textSize = 14f
            onClick {  }
        }.lparams {
            gravity = Gravity.END
            topMargin = dip(20)
            rightMargin = dip(20)
            bottomMargin = dip(20)
        }
    }
}
