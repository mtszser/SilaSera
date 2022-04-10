package com.example.silasera.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.silasera.ForgotPassword
import com.example.silasera.R
import com.example.silasera.SignUp
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var dbrefer: FirebaseAuthException
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val signUp = findViewById<TextView>(R.id.sign_up_button)
        signUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out)
        }

        val forgotPassword = findViewById<TextView>(R.id.forgot_password)
        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        val continueGuest = findViewById<TextView>(R.id.guestButton)
        continueGuest.setOnClickListener {
            val intent = Intent(this, GuestMainMenu::class.java)
            startActivity(intent)
        }
    }
}






