package com.example.silasera.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.silasera.ForgotPassword
import com.example.silasera.R
import com.example.silasera.SignUp
import com.example.silasera.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 69
    private var backPressedTime: Long = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signInButton.setOnClickListener {
            signIn()
        }
        signOut()
        firebaseAuth.signOut()
        revokeAccess()




    binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val welcome = email.substringBefore("@")

                        Toast.makeText(this, "Welcome $welcome", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, GuestMainMenu::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "Incorrect e-mail address or password.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_LONG).show()
            }

        }


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

    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        updateUI(currentUser)
        // Check if user is signed in (non-null) and update UI accordingly.
        //If REMEMBER ME is on.
//        if (currentUser != null) {
//            Toast.makeText(this, "Welcome Back!", Toast.LENGTH_SHORT).show()
//            updateUI(currentUser)
        // Its first time or REMEMBER ME is off.
//        } else {
//            Toast.makeText(this, "Hello, SIGN IN to get FIT!", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onBackPressed() {
//        if(backPressedTime + 2000 > System.currentTimeMillis()) {
//            super.onBackPressed()
//            finish()
//        } else {
//            Toast.makeText(this, "Wcisnij jeszcze raz by wyłączy", Toast.LENGTH_LONG).show()
//        }
//        backPressedTime = System.currentTimeMillis()
        finish()
    }


    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }


    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            firebaseUser(account?.idToken!!)
            // Signed in successfully

            val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

            val myIntent = Intent(this, GuestMainMenu::class.java)
            myIntent.putExtra("gFirstName", googleFirstName)
            myIntent.putExtra("gProfilePicUrl", googleProfilePicURL)
            startActivity(myIntent)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "Google sign in fail", e.statusCode.toString()
            )
        }
    }

    private fun firebaseUser(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this) {task ->
            if(task.isSuccessful) {
                //Sign in Success, updates UI with signed user info
                val user = firebaseAuth.currentUser
                updateUI(user)

            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "Sign in with created google credential failed", task.exception)
                updateUI(null)
            }
        }
    }



    private fun updateUI(user: FirebaseUser?) {
        val googleToken = user?.uid
        val googleName = user?.displayName
        val googlePhoto = user?.photoUrl.toString()

    }




    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }

}







