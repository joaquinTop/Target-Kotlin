package com.joaquin.toptierlabs.targetmvd.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.business.ServiceProvider
import com.joaquin.toptierlabs.targetmvd.models.ApiError
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.models.responses.SignInResponse
import com.joaquin.toptierlabs.targetmvd.serializers.UserSerializer
import com.joaquin.toptierlabs.targetmvd.services.AuthenticationService
import com.joaquin.toptierlabs.targetmvd.ui.activities.UpNavigatorActivity
import com.joaquin.toptierlabs.targetmvd.ui.components.SignUpView
import com.joaquin.toptierlabs.targetmvd.utils.RxBus
import io.realm.Realm
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Santiago Cirillo on 10/17/17.
 */
public class SignUpFragment : Fragment() {

    val ARG_ITEM_ID = "item_id"
    internal var textView: TextView? = null
    private val signUpView = SignUpView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Do something like this with DI
        val appBarLayout = activity.find<android.support.v7.widget.Toolbar>(R.id.toolbar_layout)
        if (appBarLayout != null) {
            appBarLayout.title = "Sing Up"
        }
        setListeners()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = signUpView.bind(this)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        signUpView.unbind(this)
    }

    private fun setListeners() {
        RxBus.listen(SignInResponse::class.java).subscribe(
                { response ->
                    startActivity(Intent(activity, UpNavigatorActivity::class.java))
                })
        RxBus.listen(ApiError::class.java).subscribe(
                { error ->
                    toast(R.string.signInError)
                })
    }

    fun signUp(user: User) {
        // signUpView.spinner.visibility = View.VISIBLE
        val userSerializer: UserSerializer = UserSerializer()
        userSerializer.mUser = user
        val service = ServiceProvider.create(AuthenticationService::class.java)

        val realm = Realm.getDefaultInstance()

        service.signUp(userSerializer)
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
                        })
    }

}
