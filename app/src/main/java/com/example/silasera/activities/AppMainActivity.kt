package com.example.silasera.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.silasera.R
import com.example.silasera.databinding.SilaSeraMainBinding

class AppMainActivity : AppCompatActivity() {

    private lateinit var binding: SilaSeraMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SilaSeraMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserUid()


    }

    private fun getUserUid() {
        val userIntent = intent
        val userUid = userIntent.getStringExtra("uid")
        Log.i("useruid", "$userUid")
        binding.silaSeraProfileUid.text = userUid
    }

    override fun onBackPressed() {
        finish()
    }
}