package com.example.silasera.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R
import com.example.silasera.databinding.AppEditProfileBinding
import com.example.silasera.databinding.EpItemAvatarBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class EditProfile : Fragment() {

    private lateinit var binding: EpItemAvatarBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var profileName: TextView
    private lateinit var profileLastName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profileWeight: TextView
    private lateinit var profileAge: TextView
    private lateinit var profileHeight: TextView
    private lateinit var profileCPM: TextView
    private lateinit var userProfileUid: String
    private lateinit var profileEditButton: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = EpItemAvatarBinding.inflate(inflater, container, false)

        getTextViews()
        getButtons()
        getProfileInfo()
        return binding.root
    }

    private fun getProfileInfo() {
        val getBundle = arguments
        val userUid = getBundle!!.getString("userUid").toString()
        userProfileUid = userUid
        Log.i("userUidED", "$userUid")

        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.child(userUid).get().addOnSuccessListener {
            Log.i("Profile Info", "${it.value}")
            val userName = it.child("userName").value.toString()
            val userLastName = it.child("userLastname").value.toString()
            val userHeight = it.child("userHeight").value.toString()
            val userAge = it.child("userAge").value.toString()
            val userWeight = it.child("userWeight").value.toString()
            val userCPM = it.child("userCPM").value.toString()
            val userEmail = it.child("userEmail").value.toString()
            Log.i("Profile Name", userName)

            profileName.text = userName
            profileLastName.text= userLastName
            profileEmail.text = userEmail
            profileAge.text = "$userAge lat/a"
            profileWeight.text = "$userWeight kg"
            profileCPM.text = "$userCPM kcal"
            profileHeight.text = "$userHeight cm"


        }
    }

    private fun getButtons() {
        profileEditButton = binding.appProfileEditButton

        profileEditButton.setOnClickListener{
            val editProfile2 = EditProfile2()
            val bundle = Bundle()
            bundle.putString("userUid", userProfileUid)
            editProfile2.arguments = bundle
            val transaction: FragmentTransaction =  requireFragmentManager().beginTransaction()
            transaction.replace(R.id.app_frame_layout, editProfile2)
            transaction.commit()
        }


    }

    private fun getTextViews() {
        profileName = binding.appProfileName
        profileLastName = binding.appProfileLastname
        profileHeight = binding.appProfileHeight
        profileWeight = binding.appProfileWeight
        profileAge = binding.appProfileAge
        profileEmail = binding.appProfileEmail
        profileCPM = binding.appProfileCpm
    }


}