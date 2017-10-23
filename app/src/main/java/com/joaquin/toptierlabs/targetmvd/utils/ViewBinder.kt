package com.joaquin.toptierlabs.targetmvd.utils

import android.view.View

/**
 * Created by Santiago Cirillo on 10/17/17.
 */
interface ViewBinder<in T> {
    fun bind(t: T): View
    fun unbind(t: T)
}
