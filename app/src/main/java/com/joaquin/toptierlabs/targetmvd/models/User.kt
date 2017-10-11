package com.joaquin.toptierlabs.targetmvd.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class User: RealmObject() {

    @PrimaryKey
    @SerializedName("email")
    @Expose
    open var mEmail: String? = null

    @SerializedName("password")
    @Expose
    open var mPassword: String? = null

    @SerializedName("password_confirmation")
    @Expose
    open var mPasswordConfirmation: String? = null

    @SerializedName("name")
    @Expose
    open var mName: String? = null

    @SerializedName("gender")
    @Expose
    open var mGender: String? = null
}