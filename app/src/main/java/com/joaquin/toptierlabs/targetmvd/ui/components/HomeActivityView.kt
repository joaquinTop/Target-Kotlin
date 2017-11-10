package com.joaquin.toptierlabs.targetmvd.ui.components

import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.ui.activities.SignInActivity
import org.jetbrains.anko.*

class HomeActivityView : AnkoComponent<SignInActivity> {

    // UI Refs

    override fun createView(ui: AnkoContext<SignInActivity>) = with(ui) {

        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundResource = R.color.primaryBackgroundColor
        }
    }

}
