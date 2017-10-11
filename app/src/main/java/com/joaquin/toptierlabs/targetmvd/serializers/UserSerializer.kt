package com.joaquin.toptierlabs.targetmvd.serializers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.joaquin.toptierlabs.targetmvd.models.User
import io.realm.RealmObject
import io.realm.annotations.RealmClass


@RealmClass
open class UserSerializer: RealmObject() {

    @SerializedName("user")
    @Expose
    open var mUser: User? = null
}

//
//data class UserSerializer(
//        @SerializedName("user") val mUser: User
//)