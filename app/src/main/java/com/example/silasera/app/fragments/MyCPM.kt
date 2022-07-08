package com.example.silasera.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R
import com.example.silasera.databinding.AppMyCpmBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference

class MyCPM : Fragment() {

    private lateinit var binding: AppMyCpmBinding
    private lateinit var cpmWeight: TextInputEditText
    private lateinit var cpmHeight: TextInputEditText
    private lateinit var checkMan: MaterialCheckBox
    private lateinit var profileUid: String
    private lateinit var checkWoman: MaterialCheckBox
    private lateinit var cpmAge: TextInputEditText

    private var gender = "M"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AppMyCpmBinding.inflate(inflater, container, false)

        checkMan = binding.appMyCpmMan
        checkWoman = binding.appMyCpmWoman
        getUserUid()
        getButtons()
        return binding.root
    }

    private fun getUserUid() {
        val getBundle = arguments
        val userUid = getBundle!!.getString("userUid").toString()
        profileUid = userUid
    }


    private fun getButtons() {
        checkMan.setOnClickListener {
            if (checkMan.isChecked) {
                checkWoman.isChecked = false
                gender = "M"
            }
        }

        checkWoman.setOnClickListener{
            if (checkWoman.isChecked) {
                checkMan.isChecked = false
                gender = "W"
            }
        }
        binding.appMyCpmNext.setOnClickListener{

            getInputs()
            countBMR()


        }
        binding.appMyCpmClean.setOnClickListener{

            getInputs()
            cleanInputs()

        }
    }

    private fun countBMR() {
        if (cpmWeight.text?.isNotEmpty() == true && cpmHeight.text?.isNotEmpty() == true && cpmAge.text?.isNotEmpty() == true && checkMan.isChecked || checkWoman.isChecked) {
            val cpmWeight = cpmWeight.text.toString().toDouble()
            val cpmHeight = cpmHeight.text.toString().toDouble()
            val cpmAge = cpmAge.text.toString().toInt()

            if (cpmWeight <= 0.0 || cpmWeight > 150.0 || cpmHeight <= 0.0 || cpmAge < 18
                || cpmAge > 99.9 || cpmHeight > 231){

                Toast.makeText(context, "Dane są nieprawidłowe.", Toast.LENGTH_SHORT).show()
            } else {

                // PPM (kobiety) =  (10 x masa ciała [kg])+(6,25 x wzrost [cm])-(5 x [wiek]) – 161
                //
                //PPM (mężczyźni) = (10 x masa ciała [kg])+(6, 25 x wzrost [cm])-(5 x [wiek]) + 5


                val yourBMR = if (gender == "W") {
                    // for woman (false)
                    (10 * cpmWeight) + (6.25 * cpmHeight) - (5 * cpmAge) - 161
                } else { // for man (true)
                    (10 * cpmWeight) + (6.25 * cpmHeight) - (5 * cpmAge) + 5
                }
                sendBundle(yourBMR)
            }
        } else {
            Toast.makeText(context, "Wpisz/Zaznacz dane aby obliczyć CPM", Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendBundle(myBMR: Double) {
        val bundle = Bundle()
        bundle.putDouble("myBMR", myBMR)
        bundle.putString("profileUid", profileUid)
        Log.i("MyCPM userUid", profileUid)

        //Transfer to MyCPM2 fragment
        val myCPM2 = MyCPM2()

        Log.i("myBMR", "$myBMR")
        myCPM2.arguments = bundle
        val transaction: FragmentTransaction =  requireFragmentManager().beginTransaction()
        transaction.replace(R.id.app_frame_layout, myCPM2)
        transaction.commit()
    }

    private fun cleanInputs() {
        if (cpmWeight.text?.isNotEmpty() == true || cpmHeight.text?.isNotEmpty() == true || cpmAge.text?.isNotEmpty() == true) {
            cpmWeight.text?.clear()
            cpmHeight.text?.clear()
            cpmAge.text?.clear()
        } else {
            Toast.makeText(context, "Pola są puste.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getInputs() {
        cpmWeight = binding.appMyCPMWeight
        cpmHeight = binding.appMyCPMHeight
        cpmAge = binding.appMyCPMAge
    }
}