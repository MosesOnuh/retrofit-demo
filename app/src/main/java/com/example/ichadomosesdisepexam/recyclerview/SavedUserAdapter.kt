package com.example.ichadomosesdisepexam.recyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ichadomosesdisepexam.R
import com.example.ichadomosesdisepexam.model.ProfileResponse



class SavedUserAdapter() : RecyclerView.Adapter<SavedUserViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<ProfileResponse>() {
        override fun areItemsTheSame(oldItem: ProfileResponse, newItem: ProfileResponse): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ProfileResponse, newItem: ProfileResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedUserViewHolder {
        return SavedUserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_saved_profile,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener: ((ProfileResponse) -> Unit)? = null

    override fun onBindViewHolder(holder: SavedUserViewHolder, position: Int) {
        val profileResponse = differ.currentList[position]
        holder.userImage.load(profileResponse.profileImage) {
            error(R.drawable.ic_baseline_account_circle_24)
            placeholder(R.drawable.ic_baseline_account_circle_24)
        }
        holder.userDetail.text = profileResponse.name.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(profileResponse) }
        }
    }
    fun setOnItemClickListener(listener: (ProfileResponse) -> Unit) {
        onItemClickListener = listener
    }
}

