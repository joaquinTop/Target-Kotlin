package com.joaquin.toptierlabs.targetmvd.services

import com.joaquin.toptierlabs.targetmvd.models.responses.SignInResponse
import com.joaquin.toptierlabs.targetmvd.serializers.UserSerializer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import rx.Observable

interface AuthenticationService {

    @POST("users")
    fun signUp(@Body user: UserSerializer): Observable<SignInResponse>

    @POST("users/sign_in")
    fun signIn(@Body user: UserSerializer): Observable<SignInResponse>

    @DELETE("users/sign_out")
    fun signOut(): Call<Void>

//    @POST("users/facebook")
//    fun facebook(@Body token: Facebook): Call<UserSerializer>
//
//    @POST("users/confirmation")
//    fun confirmation(@Body user: UserSerializer): Call<Void>
//
//    @POST("users/password")
//    fun resetPassword(@Body user: UserSerializer): Call<Void>
}
