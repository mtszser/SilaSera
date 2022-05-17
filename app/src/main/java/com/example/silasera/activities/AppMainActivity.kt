package com.example.silasera.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
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
    private lateinit var userProfile: ArrayList<UserProfile>
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sila_sera_main)

        drawerLayout = findViewById(R.id.app_drawer_layout)
        navView = findViewById(R.id.nav_view)




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
//        getUserUid()
//        userProfile = arrayListOf()
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



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
//    private fun getUserUid() {
//        val userIntent = intent
//        val userUid = userIntent.getStringExtra("uid")
//        Log.i("useruid", "$userUid")
//        binding.silaSeraProfileUid.text = userUid
//        isInFirebase(userUid)
//    }
//
//    private fun isInFirebase(userUid: String?) {
//        dbReference = FirebaseDatabase.getInstance().getReference("userProfile")
//        dbReference.child("111222333444555").get().addOnSuccessListener {
//            Log.i("firebaseUser", "info: ${it.value}")
//            val firstname = it.child("username").value.toString()
//            val secondname = it.child("userlastname").value
//            val height = it.child("height").value
//            val weight = it.child("weight").value
//            Log.i("name", "$firstname")
//            binding.serName.text = firstname
//            binding.serLastname.text = secondname.toString()
//            binding.serHeight.text = height.toString()
//            binding.serWeight.text = weight.toString()
//
//        }.addOnFailureListener{
//            Log.i("FirebaseUser", "user not Found $it")
//        }
//
//    }


    override fun onBackPressed() {
    }
}