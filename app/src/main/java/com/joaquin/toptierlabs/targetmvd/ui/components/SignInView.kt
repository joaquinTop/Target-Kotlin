package com.joaquin.toptierlabs.targetmvd.ui.components

import android.support.design.widget.TextInputLayout
import android.support.v7.widget.AppCompatEditText
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.ui.activities.SignInActivity
import com.joaquin.toptierlabs.targetmvd.ui.components.custom.customLoading
import com.joaquin.toptierlabs.targetmvd.utils.*
import org.jetbrains.anko.*


class SignInView : AnkoComponent<SignInActivity> {

    // UI Refs
    lateinit var email: AppCompatEditText
    lateinit var password: AppCompatEditText
    lateinit var spinner: RelativeLayout
    lateinit var tilemail: TextInputLayout
    lateinit var tilPassword: TextInputLayout

    override fun createView(ui: AnkoContext<SignInActivity>) = with(ui) {

        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundResource = R.color.primaryBackgroundColor

            textView {
                id = R.id.appTitleTextView
                textResource = R.string.app_title
                textSize = 25f
                textColor = resources.getColor(R.color.defaultTextColor)
                typeface = bold
            }.lparams {
                width = wrapContent
                height = wrapContent
                topMargin = dip(80)
                centerHorizontally()
            }

            relativeLayout {
                linearLayout {
                    id = R.id.signInUsernameWrapper
                    tilemail = textInputLayout {
                        layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)

                        setErrorTextAppearance(R.style.AppTextAppearanceError)

                        email = appCompatEditText {
                            hintResource = R.string.sign_in_email_hint
                            textColor = resources.getColor(R.color.defaultTextColor)
                            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                            singleLine = true
                            textSize = 18f
                            textChangedListener {
                                onTextChanged { text, start, before, count ->
                                    tilemail.isErrorEnabled = false
                                }
                            }
                        }
                    }

                }.lparams {
                    width = dip(250)
                    centerHorizontally()
                }

                linearLayout {
                    id = R.id.signInPasswordWrapper
                    tilPassword = textInputLayout {
                        layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)

                        setErrorTextAppearance(R.style.AppTextAppearanceError)
                        isPasswordVisibilityToggleEnabled = true

                        password = appCompatEditText {
                            id = R.id.signInUsernameWrapper
                            hintResource = R.string.sign_in_password_hint
                            singleLine = true
                            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            textColor = resources.getColor(R.color.defaultTextColor)
                            textSize = 18f
                            textChangedListener {
                                onTextChanged { text, start, before, count ->
                                    tilPassword.isErrorEnabled = false
                                }
                            }
                        }
                    }
                }.lparams {
                    width = dip(250)
                    centerHorizontally()
                    topMargin = dip(30)
                    below(R.id.signInUsernameWrapper)
                }

                styledButton {
                    id = R.id.signInButton
                    textResource = R.string.sign_in_button
                    typeface = semibold
                    backgroundResource = R.drawable.selector_button_dark
                    onClick {
                        handleOnSignInButtonPressed(
                                ui = ui,
                                username = email.text.toString(),
                                password = password.text.toString())
                    }
                }.lparams {
                    width = dip(130)
                    topMargin = dip(30)
                    below(R.id.signInPasswordWrapper)
                    centerHorizontally()
                }

                textView {
                    id = R.id.forgotYourPassword
                    textResource = R.string.forgot_your_password_text
                    textSize = 14f
                    textColor = color(R.color.defaultTextColor)
                    typeface = semibold
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    topMargin = dip(20)
                    below(R.id.signInButton)
                    centerHorizontally()
                }

                textView {
                    id = R.id.connectWithFacebook
                    textResource = R.string.connect_with_facebook_text
                    textSize = 14f
                    textColor = color(R.color.facebookPrimaryColor)
                    typeface = bold
                    onClick {
                        handleConnectFacebookPressed(ui = ui)
                    }
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    topMargin = dip(22)
                    below(R.id.forgotYourPassword)
                    centerHorizontally()
                }

            }.lparams {
                width = wrapContent
                height = wrapContent
                horizontalPadding = dip(10)
                centerInParent()
            }

            verticalLayout {
                view {
                    id = R.id.signInSeparator
                    backgroundResource = R.color.secondaryBackgroundColor
                }.lparams {
                    width = dip(130)
                    height = dip(1)
                }
                textView {
                    id = R.id.signUpLink
                    textResource = R.string.sign_up_text
                    textSize = 14f
                    textColor = color(R.color.defaultTextColor)
                    typeface = semibold
                    onClick {
                        handleOnSignUpButtonPressed(ui = ui)
                    }
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    gravity = Gravity.CENTER_HORIZONTAL
                    topMargin = dip(18)
                }
            }.lparams {
                width = wrapContent
                height = wrapContent
                centerHorizontally()
                alignParentBottom()
                bottomMargin = dip(60)
            }

            spinner = customLoading().lparams {
                centerInParent()
                width = wrapContent
                height = wrapContent
            }
            spinner.visibility = View.INVISIBLE
        }
    }

    private fun handleOnSignInButtonPressed(ui: AnkoContext<SignInActivity>, username: String, password: String) {
        if (username.isBlank()) {
            tilemail.isErrorEnabled = true
            tilemail.error = ui.ctx.getString(R.string.error_invalid_email);
        } else if (password.isBlank()) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = ui.ctx.getString(R.string.error_invalid_password);
        } else {
            spinner.visibility = View.VISIBLE
            ui.owner.authorizeUser(username, password)
        }
    }

    private fun handleOnSignUpButtonPressed(ui: AnkoContext<SignInActivity>) {
        ui.owner.openSignUp()
    }

    private fun handleConnectFacebookPressed(ui: AnkoContext<SignInActivity>) {
        ui.owner.openFacebook()
    }
}
