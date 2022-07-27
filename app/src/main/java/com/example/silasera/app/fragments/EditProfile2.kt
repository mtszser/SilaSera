package com.example.silasera.app.fragments

import android.icu.util.Calendar
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R
import com.example.silasera.databinding.EpEditProfileBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class EditProfile2 : Fragment() {

    private lateinit var binding: EpEditProfileBinding
    private lateinit var profileEditName: TextInputEditText
    private lateinit var profileEditLastName: TextInputEditText
    private lateinit var dbReference: DatabaseReference
    private lateinit var profileEditEmail: TextInputEditText
    private lateinit var profileEditHeight: TextInputEditText
    private lateinit var profileEditWeight: TextInputEditText
    private lateinit var birthdayPicker: DatePicker
    private lateinit var exitProfile: ImageButton
    private var profileYear = 0
    private lateinit var userProfileUid: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = EpEditProfileBinding.inflate(inflater, container, false)
        getInputs()
        getData()
        pickDate()


        return binding.root

    }

    private fun backToProfile() {
        val myProfile = EditProfile()
        val bundle = Bundle()
        bundle.putString("userUid", userProfileUid)
        myProfile.arguments = bundle
        val transaction: FragmentTransaction =  requireFragmentManager().beginTransaction()
        transaction.replace(R.id.app_frame_layout, myProfile)
        transaction.commit()
    }

    private fun getInputs() {
        profileEditName = binding.epProfileName
        profileEditLastName = binding.epProfileLastname
        profileEditEmail = binding.epProfileEmail
        profileEditHeight = binding.epProfileHeight
        profileEditWeight = binding.epProfileWeight
        exitProfile = binding.appProfileExitButton
        exitProfile.setOnClickListener{
            backToProfile()
        }

    }

    private fun getData() {
        val getBundle = arguments
        val userUid = getBundle!!.getString("userUid").toString()
        userProfileUid = userUid

        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.child(userUid).get().addOnSuccessListener {
            Log.i("Profile Info", "${it.value}")
            val userName = it.child("userName").value.toString()
            val userLastName = it.child("userLastname").value.toString()
            val userHeight = it.child("userHeight").value.toString().toDouble()
            val userWeight = it.child("userWeight").value.toString().toDouble()
            val userEmail = it.child("userEmail").value.toString()

            profileEditName.setText(userName)
            profileEditLastName.setText(userLastName)
            profileEditEmail.setText(userEmail)
            profileEditHeight.setText(userHeight.toString())
            profileEditWeight.setText(userWeight.toString())


//            val profileName = profileEditName.text.toString()
//            val profileLastname = profileEditLastName.text.toString()
//            val profileHeight = profileEditHeight.text.toString()
//            val profileWeight = profileEditWeight.text.toString()
            updateProfile()
        }
    }

    private fun updateProfile() {
        binding.appProfileSave.setOnClickListener {

            Log.i("Profile HashMap", "$userProfileUid")

            dbReference = FirebaseDatabase.getInstance().getReference("UserProfile").child(userProfileUid)
            dbReference.child("userName").setValue(profileEditName.text.toString())
            dbReference.child("userLastname").setValue(profileEditLastName.text.toString())
            dbReference.child("userAge").setValue(profileYear)
            dbReference.child("userWeight").setValue(profileEditWeight.text.toString().toDouble())
            dbReference.child("userHeight").setValue(profileEditHeight.text.toString().toDouble())
            Toast.makeText(context,"Dane zostałe zapisane.",
                Toast.LENGTH_SHORT
            ).show()
            backToProfile()

        }



    }

    private fun pickDate() {
            birthdayPicker = binding.appProfileBirthday
            val today = Calendar.getInstance()
            var currentYear = Calendar.getInstance().get(Calendar.YEAR)

            birthdayPicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            ) { _, year, month, day ->

                profileYear = currentYear - year
            }




        }

    }
