package com.joaquin.toptierlabs.targetmvd.serializers


import com.google.gson.annotations.SerializedName
import com.joaquin.toptierlabs.targetmvd.models.User


open class UserSerializer {

    @SerializedName("user")
    open var mUser: User? = null
}

//
//data class UserSerializer(
//        @SerializedName("user") val mUser: User
//)
