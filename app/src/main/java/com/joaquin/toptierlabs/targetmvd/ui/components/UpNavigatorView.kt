package com.joaquin.toptierlabs.targetmvd.ui.components

import android.view.View
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.ui.activities.UpNavigatorActivity
import com.joaquin.toptierlabs.targetmvd.utils.ViewBinder
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout

/**
 * Created by Santiago Cirillo on 10/17/17.
 */

internal class UpNavigatorView : ViewBinder<UpNavigatorActivity> {
    override fun bind(t: UpNavigatorActivity): View {
        return t.UI {
            coordinatorLayout {
                lparams(width = matchParent, height = matchParent)
                appBarLayout(R.style.AppTheme_AppBarOverlay) {
                    lparams(width = matchParent, height = wrapContent)
                    t.toolbar = toolbar {
                        id = R.id.toolbar_layout
                        //  popupTheme = R.style.AppTheme_PopupOverlay
                        backgroundColor = R.color.colorPrimary
                        t.setSupportActionBar(this)
                        t.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    }
                }
                t.detailContainer = relativeLayout {
                    id = R.id.item_detail_container
                }
            }
        }.view
    }

    override fun unbind(t: UpNavigatorActivity) {
        t.toolbar = null
        t.detailContainer = null
    }
}
