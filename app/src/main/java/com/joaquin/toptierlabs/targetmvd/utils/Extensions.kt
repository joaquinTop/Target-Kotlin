package com.joaquin.toptierlabs.targetmvd.utils

import android.graphics.Typeface
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.AppCompatEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Button
import com.joaquin.toptierlabs.targetmvd.R
import org.jetbrains.anko.custom.ankoView

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun ViewManager.appCompatEditText(theme: Int = 0, init: AppCompatEditText.() -> Unit) = ankoView(::AppCompatEditText, theme, init)

inline fun ViewManager.textInputLayout(theme: Int = R.style.AppTextInputLayout, init: TextInputLayout.() -> Unit) = ankoView(::TextInputLayout, theme, init)

inline fun ViewManager.styledButton (theme: Int = R.style.AppButton, init: Button.() -> Unit) = ankoView(::Button, theme, init)

//inline fun ViewManager.styledDialog (theme: Int = R.style.AppButton, init: AlertDialogBuilder.() -> Unit) = ankoView(::AlertDialogBuilder, theme, init)

//inline fun ViewManager.indeterminateProgressDialogStyled (theme: Int = R.style.AppSpinnerLayout, init: it.() -> Unit) = ankoView(::ProgressDialog, theme, init)

val View.bold: Typeface
    get() =
    Typeface.createFromAsset(context.assets, "fonts/OpenSans-Bold.ttf")
val View.boldItalic: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-BoldItalic.ttf")
val View.extraBold: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-ExtraBold.ttf")
val View.extraBoldItalic: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-ExtraBoldItalic.ttf")
val View.italic: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-Italic.ttf")
val View.light: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-Light.ttf")
val View.lightItalic: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-LightItalic.ttf")
val View.regular: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-Regular.ttf")
val View.semibold: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-Semibold.ttf")
val View.semiboldItalic: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/OpenSans-SemiboldItalic.ttf")