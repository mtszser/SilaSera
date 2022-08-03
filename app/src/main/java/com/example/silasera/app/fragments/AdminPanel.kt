package com.example.silasera.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.silasera.R
import com.example.silasera.databinding.AppAdminPanelBinding
import com.example.silasera.dataclass.MyClients
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AdminPanel : Fragment() {

    private lateinit var binding: AppAdminPanelBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var dbSpinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AppAdminPanelBinding.inflate(inflater, container, false)

        getData()
        return binding.root
    }

    private fun getData() {
        dbSpinner = binding.appAdminSpinner
        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.get().addOnSuccessListener { it ->
            val arrayList: ArrayList<String> = arrayListOf()
            it.children.forEach {
                val item = MyClients (
                    it.child("userName").value.toString(),
                    it.child("userLastname").value.toString(),
                    it.key.toString(),
                )
                arrayList.add(item.toString())
            }

            val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, arrayList)
            dbSpinner.adapter = spinnerAdapter


        }
    }



    }
