package com.yaka.logme

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.accountkit.AccessToken
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType

class MainActivity : AppCompatActivity() {

    val APP_REQUEST_CODE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FontHelper.setCustomTypeface(findViewById(R.id.view_root))
        var accessToken:AccessToken? = AccountKit.getCurrentAccessToken()
        if (accessToken!=null){
            activitylauncher()
        }
    }
    override
    protected fun onActivityResult(
            requestCode:Int,
            resultCode:Int,
            data:Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == APP_REQUEST_CODE)
        { // confirm that this response matches your request
            var loginResult:AccountKitLoginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY)
            var toastMessage:String
            if (loginResult.getError() != null)
            {
                toastMessage = loginResult.error.toString()
                Toast.makeText(this,toastMessage,Toast.LENGTH_LONG).show()
            }

            else if (loginResult.getAccessToken() != null) {
                    activitylauncher()

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.
                // Success! Start your next activity...

            }
            // Surface the result to your user in an appropriate way.

        }
    }


    private fun onLogin(loginType: LoginType) {
        // create intent for the Account Kit activity
        val intent = Intent(this, AccountKitActivity::class.java)
        // configure login type and response type
        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(
                loginType,
                AccountKitActivity.ResponseType.TOKEN
        )
        configurationBuilder.setDefaultCountryCode("IN")
        val configuration = configurationBuilder.build()

        // launch the Account Kit activity
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration)
        startActivityForResult(intent, APP_REQUEST_CODE)
    }

    fun onPhoneLogin(view: View){
        //onLogin(LoginType.PHONE)
        val intent = Intent(this, AccountKitActivity::class.java)
        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(
                    LoginType.PHONE,
                    AccountKitActivity.ResponseType.TOKEN) // or .ResponseType.TOKEN
        configurationBuilder.setDefaultCountryCode("IN")
            // ... perform additional configuration ...
        intent.putExtra(
                    AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                    configurationBuilder.build())
        startActivityForResult(intent, APP_REQUEST_CODE)

    }

    fun onEmailLogin(view: View){
        onLogin(LoginType.EMAIL)
    }

    fun activitylauncher(){
        var intent:Intent = Intent(this,AccountActivity::class.java)
        startActivity(intent)
        finish()
    }
}
