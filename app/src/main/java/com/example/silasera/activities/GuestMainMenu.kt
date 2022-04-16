package com.example.silasera.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.silasera.ForgotPassword
import com.example.silasera.R
import com.example.silasera.adapters.CardDatasAdapter
import com.example.silasera.dataclass.GuestCard
import com.example.silasera.splash.SplashActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class GuestMainMenu : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_guest_main_menu)
            setCardsRecyclerView(createCards())

            val img = findViewById<ImageView>(R.id.guestAvatar)
            img.load("https://avatarfiles.alphacoders.com/105/thumb-105223.jpg") {
                crossfade(true)
                // transformacja z prostokąta na koło
                transformations(CircleCropTransformation())
            }
    }

    override fun onBackPressed() {

    }


    // Dodanie kart do Listy
    private fun createCards(): List<GuestCard> {

        val cards = ArrayList<GuestCard>()
        cards.add(GuestCard(R.drawable.womantest, "For Woman"))
        cards.add(GuestCard(R.drawable.mantest, "For Man"))
        cards.add(GuestCard(R.drawable.weightplanner, "WeightPlanner"))
        cards.add(GuestCard(R.drawable.sign_in, "Logout"))

        return cards
    }

    // Wyswietlenie kart w recyclerView
    // onClickListener na karty z rozpoczeciem nowego Activity
    private fun setCardsRecyclerView(cards: List<GuestCard>) {

        val womanIntent = Intent(this, GuestWomanWorkout::class.java)
        val manIntent = Intent(this, MainActivity::class.java)
        val weightplannerIntent = Intent(this, GuestWomanWorkout::class.java)



        val cardRecyclerView = findViewById<RecyclerView>(R.id.guest_recycler_view)
        cardRecyclerView.layoutManager = LinearLayoutManager(this)
        cardRecyclerView.adapter = CardDatasAdapter(cards){
           when (it.buttonText) {
               "For Woman" -> startActivity(womanIntent)
               "For Man" -> startActivity(manIntent)
               "WeightPlanner" -> startActivity(weightplannerIntent)
               "Logout" -> returnToMain()
           }


        }
    }

    private fun returnToMain() {
        val signInIntent = Intent(this, SplashActivity::class.java)
        Toast.makeText(this, "You have been logged out.", Toast.LENGTH_SHORT).show()
        startActivity(signInIntent)
        finish()
    }
}

