package com.joaquin.toptierlabs.targetmvd.utils

import android.view.View

/**
 * Created by Santiago Cirillo on 10/17/17.
 */
interface ViewBinder<in T, in U> {
    fun bind(t: T, u: U): View
    fun unbind(t: T)
}
