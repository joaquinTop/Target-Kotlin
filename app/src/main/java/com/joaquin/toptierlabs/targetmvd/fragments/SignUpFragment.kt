package com.joaquin.toptierlabs.targetmvd.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.business.ServiceProvider
import com.joaquin.toptierlabs.targetmvd.models.ApiError
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.models.responses.SignInResponse
import com.joaquin.toptierlabs.targetmvd.serializers.UserSerializer
import com.joaquin.toptierlabs.targetmvd.services.AuthenticationService
import com.joaquin.toptierlabs.targetmvd.ui.activities.HomeActivity
import com.joaquin.toptierlabs.targetmvd.ui.activities.UpNavigatorActivity
import com.joaquin.toptierlabs.targetmvd.ui.components.SignUpView
import com.joaquin.toptierlabs.targetmvd.utils.RxBus
import io.realm.Realm
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Santiago Cirillo on 10/17/17.
 */
class SignUpFragment : Fragment() {
    private val view = SignUpView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appBarLayout = activity.find<android.support.v7.widget.Toolbar>(R.id.toolbar_layout)
        if (appBarLayout != null) {
            appBarLayout.title = "Sing Up"
        }
        setListeners()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.bind(this, User())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view.unbind(this)
    }

    private fun setListeners() {
        RxBus.listen(SignInResponse::class.java).subscribe(
                { _ ->
                    startActivity(Intent(activity, UpNavigatorActivity::class.java))
                })
        RxBus.listen(ApiError::class.java).subscribe(
                { _ ->
                    toast(R.string.signInError)
                })
    }

    fun signUp(user: User) {
        // signUpView.spinner.visibility = View.VISIBLE
        val userSerializer: UserSerializer = UserSerializer()
        userSerializer.mUser = user
        val service = ServiceProvider.create(AuthenticationService::class.java)

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.clear(User::class.java)
        realm.copyToRealmOrUpdate(user)
        realm.commitTransaction()
        NavUtils.navigateUpTo(activity, Intent(activity, HomeActivity::class.java))

        /*  service.signUp(userSerializer)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            realm.beginTransaction()
                            realm.commitTransaction()
                            RxBus.publish(response)
                        },
                        { error ->
                            RxBus.publish(ApiError())
                            Log.e("Error", error.message)
                        })*/
    }
}
