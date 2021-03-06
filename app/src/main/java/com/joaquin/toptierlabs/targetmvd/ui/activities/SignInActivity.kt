package com.joaquin.toptierlabs.targetmvd.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.business.Authentication
import com.joaquin.toptierlabs.targetmvd.models.ApiError
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.models.responses.SignInResponse
import com.joaquin.toptierlabs.targetmvd.serializers.UserSerializer
import com.joaquin.toptierlabs.targetmvd.ui.components.SignInView
import com.joaquin.toptierlabs.targetmvd.utils.RxBus
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class SignInActivity : BaseActivity() {

    lateinit var mainUI : SignInView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = SignInView()
        mainUI.setContentView(this)

        setStatusBarTranslucent(true)
        setListeners()
    }

    fun authorizeUser(email: String, password: String) {
        val user = User()
        user.mEmail = email.trim()
        user.mPassword = password.trim()
        val userSerializer = UserSerializer()
        userSerializer.mUser = user
        Authentication.checkUserCredentials(userSerializer)
    }

    private fun setListeners() {
        RxBus.listen(SignInResponse::class.java).subscribe({ response ->
            mainUI.spinner.visibility = View.INVISIBLE
            startActivity(Intent(this, HomeActivity::class.java))
        })
        RxBus.listen(ApiError::class.java).subscribe({ error ->
            mainUI.spinner.visibility = View.INVISIBLE
            toast(R.string.signInError)
        })
    }
}
