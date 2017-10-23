package com.joaquin.toptierlabs.targetmvd.ui.components

import android.support.design.widget.TextInputLayout
import android.support.v7.widget.AppCompatEditText
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.fragments.SignUpFragment
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.ui.components.custom.customLoading
import com.joaquin.toptierlabs.targetmvd.utils.*
import org.jetbrains.anko.*


/**
 * Created by Santiago Cirillo on 10/16/17.
 */
class SignUpView : ViewBinder<SignUpFragment> {
    override fun unbind(t: SignUpFragment) {

    }

    // UI Refs
    lateinit var mEmail: AppCompatEditText
    lateinit var mPassword: AppCompatEditText
    lateinit var mPasswordConfirmation: AppCompatEditText
    lateinit var mName: AppCompatEditText
    lateinit var spinner: RelativeLayout
    lateinit var tilemail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    lateinit var tilRePassword: TextInputLayout
    lateinit var tilGender: TextInputLayout
    lateinit var tilName: TextInputLayout
    lateinit var gender: Spinner
    lateinit var selectedGender: String
    lateinit var user: User

    override fun bind(t: SignUpFragment): View {
        return t.activity.UI {
            relativeLayout {
                lparams(width = matchParent, height = matchParent)
                backgroundResource = R.color.primaryBackgroundColor
                verticalLayout {
                    lparams(width = matchParent, height = matchParent)
                    gravity = Gravity.CENTER
                    linearLayout {
                        id = R.id.signUpUsernameWrapper
                        tilemail = textInputLayout {
                            layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT)

                            setErrorTextAppearance(R.style.AppTextAppearanceError)

                            mEmail = appCompatEditText {
                                hintResource = R.string.sign_in_email_hint
                                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                                textChangedListener {
                                    onTextChanged { text, start, before, count ->
                                        tilemail.isErrorEnabled = false
                                    }
                                }
                            }
                        }
                    }.lparams {
                        width = dip(250)
                        topMargin = dip(30)
                    }

                    linearLayout {
                        tilPassword = textInputLayout {
                            layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT)

                            setErrorTextAppearance(R.style.AppTextAppearanceError)
                            isPasswordVisibilityToggleEnabled = true

                            mPassword = appCompatEditText {
                                id = R.id.signUpPasswordWrapper
                                hintResource = R.string.sign_in_password_hint
                                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                                textChangedListener {
                                    onTextChanged { text, start, before, count ->
                                        tilPassword.isErrorEnabled = false
                                    }
                                }
                            }
                        }
                    }.lparams {
                        width = dip(250)
                        topMargin = dip(30)
                    }
                    linearLayout {
                        tilRePassword = textInputLayout {
                            layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT)

                            setErrorTextAppearance(R.style.AppTextAppearanceError)
                            isPasswordVisibilityToggleEnabled = true

                            mPasswordConfirmation = appCompatEditText {
                                id = R.id.signInUsernameWrapper
                                hintResource = R.string.sign_up_reenter_password
                                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                                textChangedListener {
                                    onTextChanged { text, start, before, count ->
                                        tilRePassword.isErrorEnabled = false
                                    }
                                }
                            }
                        }
                    }.lparams {
                        width = dip(250)
                        topMargin = dip(30)
                    }
                    linearLayout {
                        tilName = textInputLayout {
                            layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT)

                            setErrorTextAppearance(R.style.AppTextAppearanceError)
                            isPasswordVisibilityToggleEnabled = true

                            mName = appCompatEditText {
                                id = R.id.signUpName
                                hintResource = R.string.sign_up_name
                                inputType = InputType.TYPE_CLASS_TEXT
                                textChangedListener {
                                    onTextChanged { text, start, before, count ->
                                        tilName.isErrorEnabled = false
                                    }
                                }
                            }
                        }
                    }.lparams {
                        width = dip(250)
                        topMargin = dip(30)
                    }
                    linearLayout {
                        id = R.id.signUpGender
                        tilGender = textInputLayout {
                            layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT)

                            var genders = arrayOf("Male", "Female")
                            var spinnerAdapter = ArrayAdapter<String>(t.activity, android.R.layout.simple_dropdown_item_1line, genders)
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            gender = spinner() {
                                id = R.id.signUpGenderWrapper
                                setAdapter(spinnerAdapter)
                                onItemSelectedListener {
                                    onItemSelected { adapterView, view, position, id ->
                                        selectedGender = genders[position]
                                        hint = selectedGender
                                    }
                                }
                            }
                        }
                    }.lparams {
                        width = dip(250)
                        topMargin = dip(30)
                    }
                    linearLayout {
                        lparams(width = wrapContent, height = matchParent)
                        gravity = Gravity.BOTTOM
                        styledButton {
                            id = R.id.signInButton
                            textResource = R.string.sign_in_button
                            typeface = semibold
                            backgroundResource = R.drawable.selector_button_dark
                            onClick {
                                handleOnSignUpButtonPressed(t,
                                        email = mEmail.text.toString(),
                                        password = mPassword.text.toString(),
                                        passwordConfirmation = mPasswordConfirmation.text.toString(),
                                        name = mName.text.toString(),
                                        gender = selectedGender)
                            }
                        }
                    }.lparams {
                        bottomMargin = dip(60)
                    }
                }
                spinner = customLoading().lparams {
                    centerInParent()
                    width = wrapContent
                    height = wrapContent
                }
                spinner.visibility = View.INVISIBLE
            }
        }.view
    }

    private fun handleOnSignUpButtonPressed(ui: SignUpFragment, email: String, password: String,
                                            passwordConfirmation: String, name: String, gender: String) {
        when (checkUserFields(email, password, passwordConfirmation, name, gender)) {
            Exception.EMAIL_ERROR -> {
                tilemail.error = ui.activity.ctx.getString(R.string.error_invalid_email)
                mEmail.requestFocus()
            }
            Exception.PASSWORD_ERROR -> {
                tilPassword.error = ui.activity.ctx.getString(R.string.error_invalid_password)
                mPassword.requestFocus()
            }
            Exception.RE_PASSWORD_ERROR -> {
                tilRePassword.error = ui.activity.ctx.getString(R.string.error_invalid_re_password)
                mPasswordConfirmation.requestFocus()
            }
            Exception.NAME_ERROR -> {
                tilName.error = ui.activity.ctx.getString(R.string.error_invalid_name)
                mName.requestFocus()
            }
            Exception.VALID_USER -> {
                user = User()
                user.mEmail = email
                user.mPassword = password
                user.mPasswordConfirmation = passwordConfirmation
                user.mName = name
                user.mGender = gender
                ui.signUp(user);
            }
        }
    }
}

