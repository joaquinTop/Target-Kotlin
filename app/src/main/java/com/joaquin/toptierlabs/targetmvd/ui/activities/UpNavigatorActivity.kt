package com.joaquin.toptierlabs.targetmvd.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.fragments.ProfileFragment
import com.joaquin.toptierlabs.targetmvd.fragments.SignUpFragment
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.ui.components.UpNavigatorView

/**
 * Created by Santiago Cirillo on 10/17/17.
 */
const val FRAGMENT_PROFILE = "fragment_profile"
const val FRAGMENT_MAPS = "fragment_maps"
const val FRAGMENT_SIGN_UP = "fragment_sign_up"

const val NAVIGATE_TO = "navigate_to"
const val CURRENT_USER = "current_user"

class UpNavigatorActivity : BaseActivity() {
    internal var detailContainer: RelativeLayout? = null
    internal var toolbar: Toolbar? = null
    internal var detailActivityLayout = UpNavigatorView()
    internal var navigateTo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateTo = intent.getStringExtra(NAVIGATE_TO) ?: throw IllegalStateException("field missing in Intent")

        setContentView(detailActivityLayout.bind(this, User()))

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when (navigateTo) {
            FRAGMENT_PROFILE -> {
                fragmentTransaction.add(R.id.item_detail_container, ProfileFragment()).commit()
            }
            FRAGMENT_SIGN_UP -> {
                fragmentTransaction.add(R.id.item_detail_container, SignUpFragment()).commit()
            }
            FRAGMENT_MAPS -> {
                //  fragmentTransaction.add(R.id.item_detail_container, MapsActivity()).commit();
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when (navigateTo) {
            FRAGMENT_PROFILE -> {
                menuInflater.inflate(R.menu.profile_menu, menu)
            }
        }
        return super.onPrepareOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, Intent(this, HomeActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
