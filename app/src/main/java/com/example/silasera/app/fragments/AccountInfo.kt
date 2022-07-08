package com.example.silasera.app.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R
import com.example.silasera.dataclass.UserProfile
import com.firebase.ui.auth.viewmodel.RequestCodes
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AccountInfo : Fragment() {

    private lateinit var accountImage: ImageView
    private var imageUri: Uri? = null
    private var pickedBitMap: Bitmap? = null
    private lateinit var dbReference: DatabaseReference

    private lateinit var aiName: TextView
    private lateinit var aiLastname: TextView
    private lateinit var aiWeight: TextView
    private lateinit var aiHeight: TextView
    private lateinit var aiSaveButton: Button
    private lateinit var aiEmail: String
    private lateinit var aiAge: TextView
    private lateinit var mCheck: CheckBox
    private lateinit var wCheck: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val appAI = inflater.inflate(R.layout.app_account_info, container, false)
        accountImage = appAI.findViewById(R.id.accountAI_avatar)
        accountImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this.requireActivity(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this.requireActivity(),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1
                )
            } else {
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, 2)
            }
        }


//        val epUserUid = appAI?.findViewById<TextView>(R.id.ep_user_uid)
//        val epUserEmail = appAI?.findViewById<TextView>(R.id.ep_user_mail)
        val info = arguments
        val profileUid = info!!.getString("userUid")
        val profileEmail = info.getString("userEmail")
        Log.i("Success Listener1", "$profileUid")
        Log.i("Success Listener2", "$profileEmail")
        Log.i("Bundle data", "Profile uid: $profileUid, Profile Email: $profileEmail")
//        val accountEmail = appAI.findViewById<TextView>(R.id.accountAI_email)
//        accountEmail.text = profileEmail
//        epUserUid?.text = profileUid.toString()
//        epUserEmail?.text = profileEmail.toString()
        onClickListeners(appAI, profileEmail, profileUid)

        return appAI
    }

    private fun onClickListeners(appAI: View?, profileEmail: String?, profileUid: String?) {
        aiSaveButton = appAI?.findViewById(R.id.accountAI_save)!!
        mCheck = appAI.findViewById(R.id.accountAI_mCheck)
        wCheck = appAI.findViewById(R.id.accountAI_wCheck)
        aiName = appAI.findViewById(R.id.accountAI_name)
        aiAge = appAI.findViewById(R.id.accountAI_age)
        aiLastname = appAI.findViewById(R.id.accountAI_lastname)
        aiWeight = appAI.findViewById(R.id.accountAI_weight)
        aiHeight = appAI.findViewById(R.id.accountAI_height)
        aiSaveButton = appAI.findViewById(R.id.accountAI_save)
        var gender = ""
        mCheck.setOnClickListener{
            if(mCheck.isChecked) {
                gender = "M"
                wCheck.isChecked = false
            }
        }
        wCheck.setOnClickListener{
            if(mCheck.isChecked) {
                gender = "K"
                mCheck.isChecked = false
            }
        }

        aiSaveButton.setOnClickListener {
            aiEmail = profileEmail!!

            val name = aiName.text.toString()
            val lastName = aiLastname.text.toString()
            val height = aiHeight.text.toString().toDouble()
            val weight = aiWeight.text.toString().toDouble()
            val age = aiAge.text.toString().toInt()
            Log.i(
                "Profile info", "email - $aiEmail, name - $name, lastName - $lastName" +
                        ", height - $height" + "weight - $weight"
            )

            dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
            val user = UserProfile(name, lastName, aiEmail, height, weight, gender, age, 0.1, 0.0)
            dbReference.child("$profileUid").setValue(user).addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Dane zostały zapisane, możesz zalogować się ponownie.",
                    Toast.LENGTH_SHORT
                ).show()
                activity?.finish()
            }


        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            accountImage.setImageURI(imageUri)
//            if(Build.VERSION.SDK_INT >= 28) {
//                val source = ImageDecoder.createSource(this.requireActivity().contentResolver, imageUri!!)
//                pickedBitMap = ImageDecoder.decodeBitmap(source)
//                accountImage.setImageBitmap(pickedBitMap)
//            } else {
//                pickedBitMap = MediaStore.Images.Media.getBitmap(this.requireActivity().contentResolver, imageUri)
//                accountImage.setImageBitmap(pickedBitMap)
//            }
//        }
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}