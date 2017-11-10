package com.joaquin.toptierlabs.targetmvd.application

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.utils.Prefs
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

val prefs: Prefs by lazy {
    TargetMVD.prefs!!
}

open class TargetMVD : Application() {

    private val DEFAULT_FONT_PATH = "fonts/OpenSans-Regular.ttf"

    companion object {
        const val CONSTANT = 12
        lateinit var typeface: Typeface
        var prefs: Prefs? = null
        val Context.TargetMVD: TargetMVD
            get() = applicationContext as TargetMVD
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
        typeface = Typeface.createFromAsset(assets, DEFAULT_FONT_PATH)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build())

        val realmConfig = RealmConfiguration.Builder(
                this).deleteRealmIfMigrationNeeded().build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}
