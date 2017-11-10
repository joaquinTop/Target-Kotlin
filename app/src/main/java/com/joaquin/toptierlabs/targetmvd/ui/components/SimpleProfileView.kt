package com.joaquin.toptierlabs.targetmvd.ui.components

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.AppCompatEditText
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.joaquin.toptierlabs.targetmvd.R
import com.joaquin.toptierlabs.targetmvd.fragments.ProfileFragment
import com.joaquin.toptierlabs.targetmvd.models.User
import com.joaquin.toptierlabs.targetmvd.utils.*
import org.jetbrains.anko.*

/**
 * Created by Santiago Cirillo on 10/30/17.
 */

class SimpleProfileView : ViewBinder<ProfileFragment, User> {
    lateinit var mName: AppCompatEditText
    lateinit var mEmail: AppCompatEditText
    lateinit var mPassword: AppCompatEditText

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun bind(t: ProfileFragment, u: User): View {
        return t.activity.UI {
            verticalLayout {
                gravity = Gravity.CENTER_HORIZONTAL
                lparams(width = matchParent, height = wrapContent)
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    verticalLayout {
                        id = R.id.profileLayout
                        gravity = Gravity.CENTER_HORIZONTAL
                        lparams(width = matchParent, height = wrapContent)
                        imageView {
                            id = R.id.headerCoverImage
                            lparams(width = matchParent, height = dip(120)) {
                                scaleType = ImageView.ScaleType.CENTER_CROP
                            }
                        }
                        imageButton {
                            id = R.id.userProfilePhoto
                            lparams(width = dip(120), height = dip(120)) {
                                background = drawable(R.drawable.profile_circular_border_imageview)
                                elevation = 5F
                                padding = 20
                                scaleType = ImageView.ScaleType.CENTER_CROP
                                imageResource = R.drawable.profile
                            }
                        }
                        verticalLayout {
                            gravity = Gravity.CENTER_HORIZONTAL
                            lparams(width = matchParent, height = wrapContent) {
                                background = drawable(R.color.errorRed)
                                elevation = 4F
                                bottomPadding = 24
                                topMargin = dip(-60)
                            }

                            textView {
                                id = R.id.profileName
                                lparams(width = wrapContent, height = wrapContent) {
                                    topMargin = dip(60)
                                    textColor = color(R.color.defaultTextColor)
                                    text = u.mName
                                    textSize = 24f
                                    typeface = bold
                                }
                            }
                        }
                    }

                    relativeLayout {
                        gravity = Gravity.CENTER_HORIZONTAL
                        lparams(width = matchParent, height = wrapContent) {
                            margin = dip(20)
                            below(R.id.profileLayout)
                        }

                        verticalLayout {
                            lparams(width = dip(250), height = wrapContent)
                            textInputLayout {
                                setErrorTextAppearance(R.style.AppTextAppearanceError)
                                mName = appCompatEditText {
                                    id = R.id.signUpName
                                    hintResource = R.string.sign_up_name
                                    inputType = InputType.TYPE_CLASS_TEXT
                                    setText(u.mName)
                                    /*textChangedListener {
                                        onTextChanged { text, start, before, count ->
                                        }
                                    }*/
                                }
                            }

                            textInputLayout {
                                setErrorTextAppearance(R.style.AppTextAppearanceError)
                                mEmail = appCompatEditText {
                                    hintResource = R.string.sign_in_email_hint
                                    inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                                    setText(u.mEmail)
                                    /*textChangedListener {
                                        onTextChanged { text, start, before, count ->
                                        }
                                    }*/
                                }
                            }

                            textInputLayout {
                                setErrorTextAppearance(R.style.AppTextAppearanceError)
                                isPasswordVisibilityToggleEnabled = true
                                mPassword = appCompatEditText {
                                    id = R.id.signUpPasswordWrapper
                                    hintResource = R.string.sign_in_password_hint
                                    setText(u.mPassword)
                                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                                    /*textChangedListener {
                                        onTextChanged { text, start, before, count ->
                                        }
                                    }*/
                                }
                            }

                            styledButton {
                                lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    topMargin = dip(30)
                                }
                                id = R.id.signInButton
                                textResource = R.string.profile_save
                                typeface = semibold
                                backgroundResource = R.drawable.selector_button_dark
                                onClick {
                                    handleOnUpdateProfileAction(ui = t,
                                            name = mName.text.toString(),
                                            password = mPassword.text.toString(),
                                            email = mEmail.text.toString())
                                }
                            }

                            textView {
                                lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    topMargin = dip(18)
                                }
                                id = R.id.signUpLink
                                textResource = R.string.log_out
                                textSize = 14f
                                textColor = color(R.color.defaultTextColor)
                                typeface = semibold

                            }
                        }
                    }
                }
            }
        }.view
    }

    override fun unbind(t: ProfileFragment) {
    }

    private fun handleOnUpdateProfileAction(ui: ProfileFragment, name: String, password: String, email: String) {
        ui.updateProfile(name, password, email)
    }
}

