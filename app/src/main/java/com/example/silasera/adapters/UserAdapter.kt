package com.example.silasera.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.dataclass.User

class UserAdapter(private val userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.MyUserHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUserHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.guest_woman_user, parent,
        false)
        return MyUserHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyUserHolder, position: Int) {
        val currentItem = userList[position]
        holder.firstName.text = currentItem.firstname
        holder.firstName
        holder.lastName.text = currentItem.lastname
    }

    override fun getItemCount(): Int = userList.size


    class MyUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val firstName: TextView = itemView.findViewById(R.id.woman_tv_firstName)
        val lastName: TextView = itemView.findViewById(R.id.woman_tv_lastName)

    }

}