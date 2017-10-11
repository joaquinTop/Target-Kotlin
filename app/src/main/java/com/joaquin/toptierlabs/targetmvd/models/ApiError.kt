package com.joaquin.toptierlabs.targetmvd.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class ApiError: RealmObject() {

    @Expose
    open var mError: String = "Error"

}