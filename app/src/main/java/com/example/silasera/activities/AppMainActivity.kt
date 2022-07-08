package com.example.silasera.activities

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isInvisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R
import com.example.silasera.app.fragments.*
import com.example.silasera.dataclass.GuestWorkout
import com.example.silasera.dataclass.UserProfile
import com.example.silasera.fragments.Bmi
import com.example.silasera.splash.SplashActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class AppMainActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userProfile: ArrayList<String>
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var profileAvatar: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileLastName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var userIntention: Intent
    private lateinit var userUid: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sila_sera_main)

        drawerLayout = findViewById(R.id.app_drawer_layout)
        navView = findViewById(R.id.nav_view)
        getUserUid()




        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId) {
                R.id.app_nav_edit_profile -> (replaceFragment(EditProfile(), it.title.toString()))
                R.id.app_nav_raport -> replaceFragment(SendRaport(), it.title.toString())
                R.id.app_nav_workout -> replaceFragment(MyWorkout(), it.title.toString())
                R.id.app_nav_my_diet -> replaceFragment(MyDiet(), it.title.toString())
                R.id.app_nav_calc_bmi -> replaceFragment(MyBMI(), it.title.toString())
                R.id.app_nav_calc_cpm -> replaceFragment(MyCPM(), it.title.toString())
                R.id.app_nav_calc_water -> replaceFragment(MyWater(), it.title.toString())
                R.id.app_nav_calc_1rm -> replaceFragment(MyRM(), it.title.toString())
                R.id.app_nav_measurement -> replaceFragment(MyBody(), it.title.toString())
                R.id.app_nav_settings -> replaceFragment(Settings(), it.title.toString())
                R.id.app_nav_logout -> logout()


            }
            true
        }

    }

    private fun logout() {
        val intent = Intent(this, SplashActivity::class.java)
        Toast.makeText(this, "Zostałeś wylogowany.", Toast.LENGTH_SHORT).show()
        startActivity(intent)
        finish()
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.app_frame_layout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
        val uidBundle = Bundle()
        uidBundle.putString("userUid", userUid)
        fragment.arguments = uidBundle





    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getUserUid() {
//        val userIntent = intent
        userIntention = intent
        userUid = userIntention.getStringExtra("uid").toString()
        val userEmail = userIntention.getStringExtra("profileEmail")
        Log.i("userUid", userUid)
        Log.i("userMail", "$userEmail")


        isInFirebase(userUid, userEmail)
    }

    private fun isInFirebase(userUid: String, userEmail: String?) {

        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.child(userUid).get().addOnSuccessListener {
            if (it.value == null) {
                getInformation(userUid, userEmail)
            } else {
                val name = it.child("userName").getValue(String::class.java)
                val lastName = it.child("userLastname").getValue(String::class.java)
                val email = it.child("userEmail").getValue(String::class.java)
                profileName = findViewById(R.id.app_username)
                profileEmail = findViewById(R.id.app_mail)
                profileName.text = name + " " + lastName
                profileEmail.text = email
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val myFragment = EditProfile()
                val appBundle = Bundle()
                appBundle.putString("userUid", userUid)
                myFragment.arguments = appBundle
                fragmentTransaction.replace(R.id.app_frame_layout, myFragment).commit()
            }

        }.addOnFailureListener{

        }
    }


    private fun getInformation(userUid: String, userEmail: String?) {
        Log.i("Success Listener", userUid)
        Log.i("Success Listener", "$userEmail")
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = AccountInfo()
        val bundle = Bundle()
        title = "Nowe Konto"
        bundle.putString("userUid", userUid)
        bundle.putString("userEmail", userEmail)
        myFragment.arguments = bundle
        fragmentTransaction.add(R.id.app_frame_layout, myFragment).commit()



    }

    override fun onBackPressed() {
    }


}
