package com.example.silasera.dataclass


import android.net.Uri

data class UserProfile(var userName: String?, var userLastname: String?, var userEmail: String? = null,
                       var userHeight: Double?, var userWeight: Double?, var userGender: String?,
                       var userAge: Int, var userBMI: Double, var userCPM: Double?,)
