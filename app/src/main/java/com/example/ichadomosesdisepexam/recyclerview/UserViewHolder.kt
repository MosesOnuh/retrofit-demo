package com.example.ichadomosesdisepexam.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ichadomosesdisepexam.R

class UserViewHolder(val userView:View) : RecyclerView.ViewHolder(userView) {
    val userImage = userView.findViewById<ImageView>(R.id.profile_image)
    val userDetail = userView.findViewById<TextView>(R.id.user_detail)

}

