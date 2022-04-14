package com.example.silasera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.silasera.activities.MainActivity
import com.example.silasera.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.regButton.setOnClickListener{
            val email = binding.signMail2.text.toString()
            val password = binding.signPassword2.text.toString()
            val confirmPass = binding.signConfirmPassword2.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){

                            Toast.makeText(this, "You can now Sign In!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        }else if(password.length < 6 && confirmPass.length < 6){
                            Toast.makeText(this, "Password must be at least 6 characters!", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Invalid email address or it's already taken.", Toast.LENGTH_LONG).show()
                        }
                    }

                } else {
                    Toast.makeText(this, "Password is not matching!", Toast.LENGTH_LONG).show()
                }
            }else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_LONG).show()
            }
        }

        val signIn = findViewById<TextView>(R.id.sign_in_button)
        signIn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.left_slide_out, R.anim.right_slide_in)
        }


        val backButton = findViewById<ImageView>(R.id.undoBtn)
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_slide_out, R.anim.right_slide_in)
        }
    }
}