package com.joaquin.toptierlabs.targetmvd.ui.activities

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.joaquin.toptierlabs.targetmvd.R



abstract class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_base, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.about -> {
                about()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    protected fun setStatusBarTranslucent(makeTranslucent: Boolean) {
        if (makeTranslucent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    private fun about() {
//        Snackbar.make(find<CoordinatorLayout>(R.id.coordinator_layout), R.string.about_message, Snackbar.LENGTH_SHORT)
//                .show()
    }

}