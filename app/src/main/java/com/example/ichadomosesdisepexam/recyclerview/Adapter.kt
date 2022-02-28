package com.example.ichadomosesdisepexam.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ichadomosesdisepexam.R
import com.example.ichadomosesdisepexam.model.Item
import coil.load



class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_profile,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Item) -> Unit)? = null

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.userImage.load(item.profileImage) {
            error(R.drawable.ic_baseline_account_circle_24)
            placeholder(R.drawable.ic_baseline_account_circle_24)
        }
        holder.userDetail.text = item.id.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(item) }

        }

    }
    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }
}

