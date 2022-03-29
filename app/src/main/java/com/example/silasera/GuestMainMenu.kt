package com.example.silasera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.cardview.widget.CardView
import coil.load
import coil.transform.CircleCropTransformation
import io.getstream.avatarview.AvatarView
import io.getstream.avatarview.coil.loadImage
import okhttp3.HttpUrl
import java.io.File

class GuestMainMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_main_menu)

        val img = findViewById<ImageView>(R.id.guestAvatar)
        img.load("https://avatarfiles.alphacoders.com/105/thumb-105223.jpg"){
            crossfade(true)
            // transformacja z prostokąta na koło
            transformations(CircleCropTransformation())
        }


    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.guest_menu, menu)
        return true;
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.guest_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }*/
}