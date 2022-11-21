package com.example.kitchenassistant.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kitchenassistant.MainActivity
import com.example.kitchenassistant.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val signUp = findViewById<TextView>(R.id.signUp)
        val logIn = findViewById<TextView>(R.id.logIn)
        val signIn = findViewById<Button>(R.id.sigIn)
        val signUpLayout = findViewById<LinearLayout>(R.id.singUpLayout)
        val logInLayout = findViewById<LinearLayout>(R.id.logInLayout)

        signUp.setOnClickListener {
            signUp.background = resources.getDrawable(R.drawable.switch_trcks, null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                signUp.setTextColor(resources.getColor(R.color.textColor, null))
            }
            logIn.background = null
            signUpLayout.visibility = View.VISIBLE
            logInLayout.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                logIn.setTextColor(resources.getColor(R.color.pinkColor, null))
            }
        }
        logIn.setOnClickListener {
            signUp.background = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                signUp.setTextColor(resources.getColor(R.color.pinkColor, null))
            }
            logIn.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signUpLayout.visibility = View.GONE
            logInLayout.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                logIn.setTextColor(resources.getColor(R.color.textColor, null))
            }
        }
        signIn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}