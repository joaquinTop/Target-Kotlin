package com.joaquin.toptierlabs.targetmvd.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.RelativeLayout
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.fragments.SignUpFragment
import com.joaquin.toptierlabs.targetmvd.ui.components.UpNavigatorView

/**
 * Created by Santiago Cirillo on 10/17/17.
 */
class UpNavigatorActivity : BaseActivity() {

    internal var detailContainer: RelativeLayout? = null
    internal var toolbar: Toolbar? = null
    internal var detailActivityLayout: UpNavigatorView = UpNavigatorView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailActivityLayout.bind(this))
        var fragment = SignUpFragment()

        getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, fragment).commit();

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
