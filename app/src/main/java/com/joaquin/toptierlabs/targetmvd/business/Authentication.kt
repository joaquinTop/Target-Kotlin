package com.joaquin.toptierlabs.targetmvd.business

import android.util.Log
import com.joaquin.toptierlabs.targetmvd.models.ApiError
import com.joaquin.toptierlabs.targetmvd.serializers.UserSerializer
import com.joaquin.toptierlabs.targetmvd.services.AuthenticationService
import com.joaquin.toptierlabs.targetmvd.utils.RxBus
import io.realm.Realm
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object Authentication {

    fun checkUserCredentials(credentials: UserSerializer) {
        val service = ServiceProvider.create(AuthenticationService::class.java)

        val realm = Realm.getDefaultInstance()

        service.signIn(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            // TODO JG: SET PREFERRED USER
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