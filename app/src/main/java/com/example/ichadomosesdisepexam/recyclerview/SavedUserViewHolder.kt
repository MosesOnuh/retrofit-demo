package com.example.ichadomosesdisepexam.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ichadomosesdisepexam.R


class SavedUserViewHolder(val userView: View) : RecyclerView.ViewHolder(userView) {
    val userImage = userView.findViewById<ImageView>(R.id.profile_image1)
    val userDetail = userView.findViewById<TextView>(R.id.user_detail1)
}
