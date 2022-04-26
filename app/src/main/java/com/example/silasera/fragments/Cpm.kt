package com.example.silasera.fragments

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.silasera.R

class Cpm : Fragment() {

    private var gender: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val cpmFragment = inflater.inflate(R.layout.fragment_cpm, container, false)

        //get Elements
        val cpmButton = cpmFragment.findViewById<Button>(R.id.cpm_button)
        val cpmWeight = cpmFragment.findViewById<EditText>(R.id.cpm_weight)
        val cpmHeight = cpmFragment.findViewById<EditText>(R.id.cpm_height)
        val cpmAge = cpmFragment.findViewById<EditText>(R.id.cpm_age)
        val cpmMan = cpmFragment.findViewById<ImageView>(R.id.cpm_man)
        val cpmWoman = cpmFragment.findViewById<ImageView>(R.id.cpm_woman)



        cpmMan.setOnClickListener{
                cpmMan.setImageResource(R.drawable.man_focus)
                cpmWoman.setImageResource(R.drawable.woman)
            gender = true

        }
        cpmWoman.setOnClickListener{
                cpmWoman.setImageResource(R.drawable.woman_focus)
                cpmMan.setImageResource(R.drawable.man)
            gender = false
            }



        cpmButton.setOnClickListener{
            Log.d("gender", "$gender")
            val weight = cpmWeight.text.toString().toDouble()
            val height = cpmHeight.text.toString().toDouble()
            val age = cpmAge.text.toString().toInt()

            // PPM (kobiety) =  (10 x masa ciała [kg])+(6,25 x wzrost [cm])-(5 x [wiek]) – 161
            //
            //PPM (mężczyźni) = (10 x masa ciała [kg])+(6, 25 x wzrost [cm])-(5 x [wiek]) + 5


            val yourPPM = if (!gender){
                    // for woman (false)
                (10 * weight) + (6.25 * height) - (5 * age) - 161
            } else { // for man (true)
                (10 * weight) + (6.25 * height) - (5 * age) + 5
            }



            //Transfer data from cpm1 fragment
            val bundle = Bundle()
            bundle.putDouble("weight", weight)
            bundle.putDouble("height", height)
            bundle.putInt("age", age)
            bundle.putDouble("ppm", yourPPM)
            bundle.putBoolean("gender", gender)

            //Transfer to CPM2 fragment
            val secondCPM = Cpm2()
            secondCPM.arguments = bundle
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.nav_host_fragment_cont, secondCPM)
            transaction.commit()
        }



        return cpmFragment
    }
    }