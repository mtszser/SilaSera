package com.example.silasera.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
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
        getUserInfo()

    }

    private fun getUserInfo() {
        val intent = getIntent()
        val gFirstName = intent.getStringExtra("gFirstName")
        val nEditText = findViewById<EditText>(R.id.greetingGuestText2)
        nEditText.setText(gFirstName)
        val gImageUrl = intent.getStringExtra("gProfilePicUrl")
        val img = findViewById<ImageView>(R.id.guestAvatar)
        img.load("$gImageUrl") {
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
        cards.add(GuestCard(R.drawable.womantest, "Dla Kobiet"))
        cards.add(GuestCard(R.drawable.mantest, "Dla Mężczyzn"))
        cards.add(GuestCard(R.drawable.weightplanner, "Zaplanuj Wagę"))
        cards.add(GuestCard(R.drawable.sign_in, "Wyloguj się"))

        return cards
    }

    // Wyswietlenie kart w recyclerView
    // onClickListener na karty z rozpoczeciem nowego Activity
    private fun setCardsRecyclerView(cards: List<GuestCard>) {

        val womanIntent = Intent(this, GuestWomanWorkout::class.java)
        val manIntent = Intent(this, MainActivity::class.java)
        val weightPlannerIntent = Intent(this, WeightPlanner::class.java)



        val cardRecyclerView = findViewById<RecyclerView>(R.id.guest_recycler_view)
        cardRecyclerView.layoutManager = LinearLayoutManager(this)
        cardRecyclerView.adapter = CardDatasAdapter(cards){
           when (it.buttonText) {
               "Dla Kobiet" -> startActivity(womanIntent)
               "Dla Mężczyzn" -> startActivity(manIntent)
               "Zaplanuj Wagę" -> startActivity(weightPlannerIntent)
               "Wyloguj się" -> returnToMain()
           }


        }
    }

    private fun returnToMain() {
        val signInIntent = Intent(this, SplashActivity::class.java)
        Toast.makeText(this, "Zostałeś wylogowany.", Toast.LENGTH_SHORT).show()
        startActivity(signInIntent)
        finish()
    }
}

