package com.example.silasera.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R
import com.example.silasera.databinding.AppMyCpm2Binding
import com.example.silasera.dataclass.UserProfile
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.roundToInt


class MyCPM2 : Fragment() {

    private lateinit var binding: AppMyCpm2Binding
    private var palNumber: Double = 1.2
    private var cpmResult: Double = 0.0
    private lateinit var userUid: String
    private lateinit var cpmSpinner: Spinner
    private lateinit var dbReference: DatabaseReference
    private lateinit var backButton: Button
    private lateinit var countButton: Button
    private lateinit var saveButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AppMyCpm2Binding.inflate(inflater, container, false)

        getData()
        getSpinnerData()

        return binding.root

    }

    private fun getProfileUid() {
        val getBundle = arguments
        val profileUid = getBundle!!.getString("userUid").toString()
        userUid = profileUid
        Log.i("profile uid is:", "$profileUid")

    }

    private fun getButton() {
        backButton = binding.appMyCpmBack
        countButton = binding.appMyCpmCount
        saveButton = binding.appMyCpmSave2
        saveCPM()
        backToBMR()

    }

    private fun saveCPM() {

        saveButton.setOnClickListener {
            if (cpmResult > 0.0) {
                dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
                dbReference.child(userUid).child("userCPM").setValue(cpmResult)
            } else {
                Toast.makeText(context, "Policz CPM, by je zapisać.", Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun backToBMR() {
        val myCPM = MyCPM()
        val bundle = Bundle()
        bundle.putString("userUid", userUid)
        myCPM.arguments = bundle
        backButton.setOnClickListener {
            val transaction: FragmentTransaction =  requireFragmentManager().beginTransaction()
            transaction.replace(R.id.app_frame_layout, myCPM)
            transaction.commit()
        }
    }


    private fun getSpinnerData() {
        val palNumbers = resources.getStringArray(R.array.palNumbers)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_style, palNumbers)
        cpmSpinner = binding.appMycpm2Pal
        cpmSpinner.adapter = arrayAdapter
        cpmSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                Toast.makeText(context, "selected pal number is: " +palNumbers[position], Toast.LENGTH_SHORT).show()
                when(position) {
                    0 ->  palNumber = 1.2
                    1 -> palNumber = 1.4
                    2 -> palNumber = 1.6
                    3 -> palNumber = 1.8
                    4 -> palNumber = 2.0
                    5 -> palNumber = 2.4
                }
                Log.d("palnumber", "$palNumber")
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                palNumber = 1.2
            }

        }
    }

    private fun getData() {
        val args = this.arguments
        val myBMR = args?.getDouble("myBMR")
        val profileUid = args?.getString("profileUid").toString()
        userUid = profileUid
        Log.i("profile uid is:", "$userUid")
        if (myBMR != null) {
            binding.appMyCPM2BMR.text = myBMR.roundToInt().toString() + "kcal."
        }
        getButton()
        getCPM(myBMR)

    }

    private fun getCPM(myBMR: Double?) {
        countButton.setOnClickListener{
            if (myBMR != null) {
                cpmResult = myBMR * palNumber
                binding.appMyCPM2Card.isVisible = true
                binding.appMyCPM2Cpm.text = cpmResult.roundToInt().toString() + "kcal."
            }
        }
    }

}