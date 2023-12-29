package com.novandi.suitmediaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.novandi.suitmediaapp.R
import com.novandi.suitmediaapp.databinding.ItemUserBinding
import com.novandi.suitmediaapp.domain.model.UserData

class UserAdapter: PagingDataAdapter<UserData, UserAdapter.ViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) holder.bind(data, onItemClickListener)
    }

    class ViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserData, onClickListener: OnItemClickListener) {
            Glide.with(itemView.context)
                .load(data.avatar)
                .placeholder(R.drawable.avatar_placeholder)
                .error(R.drawable.avatar_error)
                .into(binding.ivUser)
            binding.tvName.text = itemView.resources.getString(R.string.full_name, data.firstName, data.lastName)
            binding.tvEmail.text = data.email

            binding.cvUser.setOnClickListener {
                onClickListener.onItemClicked(data)
            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(data: UserData)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData) = oldItem == newItem
            override fun areContentsTheSame(oldItem: UserData, newItem: UserData) = oldItem.id == newItem.id
        }
    }
}