package com.example.silasera.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.silasera.R
import com.example.silasera.databinding.SilaSeraMainBinding
import com.example.silasera.dataclass.GuestWorkout
import com.example.silasera.dataclass.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AppMainActivity : AppCompatActivity() {

    private lateinit var binding: SilaSeraMainBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userProfile: ArrayList<UserProfile>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SilaSeraMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserUid()
        userProfile = arrayListOf()
    }
    private fun getUserUid() {
        val userIntent = intent
        val userUid = userIntent.getStringExtra("uid")
        Log.i("useruid", "$userUid")
        binding.silaSeraProfileUid.text = userUid
        isInFirebase(userUid)
    }

    private fun isInFirebase(userUid: String?) {
        dbReference = FirebaseDatabase.getInstance().getReference("userProfile")
        dbReference.child("111222333444555").get().addOnSuccessListener {
            Log.i("firebaseUser", "info: ${it.value}")
            val firstname = it.child("username").value.toString()
            val secondname = it.child("userlastname").value
            val height = it.child("height").value
            val weight = it.child("weight").value
            Log.i("name", "$firstname")
            binding.serName.text = firstname
            binding.serLastname.text = secondname.toString()
            binding.serHeight.text = height.toString()
            binding.serWeight.text = weight.toString()

        }.addOnFailureListener{
            Log.i("FirebaseUser", "user not Found $it")
        }

    }


    override fun onBackPressed() {
        finish()
    }
}