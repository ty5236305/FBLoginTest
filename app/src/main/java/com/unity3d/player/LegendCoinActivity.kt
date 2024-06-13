package com.unity3d.player

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.ukvision.legendcoin.R


class LegendCoinActivity : ComponentActivity() {
    private var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.setIsDebugEnabled(true)
        FacebookSdk.fullyInitialize()
        AppEventsLogger.activateApp(this.application)

        setContentView(R.layout.activity_facebook_login)
        val button = findViewById<Button>(R.id.button)


        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult) {
                        setResult(RESULT_OK)
                        finish()
                    }

                    override fun onCancel() {
                        setResult(RESULT_CANCELED)
                        finish()
                    }

                    override fun onError(error: FacebookException) {
                        // Handle exception
                    }
                })
        
        button.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this,
                listOf("user_friends", "gaming_profile", "gaming_user_picture")
            )
        }
    }



}

