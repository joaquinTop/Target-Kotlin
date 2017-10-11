package com.joaquin.toptierlabs.targetmvd.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_FILENAME = "SessionDetails"
    val LOGGED_IN = "LoggedIn"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(LOGGED_IN, value).apply()
}