package com.joaquin.toptierlabs.targetmvd.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NavUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.ui.activities.HomeActivity
import com.joaquin.toptierlabs.targetmvd.ui.components.SimpleProfileView
import io.realm.Realm
import io.realm.RealmQuery
import org.jetbrains.anko.find

/**
 * Created by Santiago Cirillo on 10/31/17.
 */

class ProfileFragment : android.support.v4.app.Fragment() {
    private val view = SimpleProfileView()
    lateinit var user: User

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Do something like this with DI
        val realm = Realm.getDefaultInstance()
        val appBarLayout = activity.find<android.support.v7.widget.Toolbar>(R.id.toolbar_layout)
        if (appBarLayout != null) {
            appBarLayout.title = "Profile"
        }
        user = RealmQuery.createQuery(realm, User::class.java).findFirst() ?: User()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.bind(this, user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view.unbind(this)
    }

    fun updateProfile(name: String, password: String, email: String) {
        val realm = Realm.getDefaultInstance()
        user = RealmQuery.createQuery(realm, User::class.java).findFirst() ?: User()
        realm.beginTransaction()
        user.mName = name
        user.mPassword = password
        user.mEmail = email
        realm.copyToRealmOrUpdate(user)
        realm.commitTransaction()
        NavUtils.navigateUpTo(activity, Intent(activity, HomeActivity::class.java))
    }
}
