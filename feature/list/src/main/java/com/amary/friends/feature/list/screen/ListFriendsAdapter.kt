package com.amary.friends.feature.list.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amary.friends.data.api.model.Friends
import com.amary.friends.feature.list.databinding.ItemViewBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class ListFriendsAdapter(
    private val onClick: (Friends) -> Unit = {}
) : ListAdapter<Friends, ListFriendsAdapter.ItemViewHolder>(FriendsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(viewBinding, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(
        private val viewBinding: ItemViewBinding,
        private val onClick: (Friends) -> Unit,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: Friends?) {
            viewBinding.apply {
                root.setOnClickListener {
                    item?.let { data -> onClick.invoke(data) }
                }
                txtName.text = item?.name
                txtBirth.text = item?.createdAt?.dateFormatter()
                Glide.with(root.context)
                    .load(item?.avatar)
                    .into(imageView)
            }
        }

        private fun String.dateFormatter(): String? {
            val inputFormat = SimpleDateFormat(ISO_FORMAT, Locale.getDefault())
            val outputFormat = SimpleDateFormat(SIMPLE_FORMAT, Locale.getDefault())
            val date = inputFormat.parse(this)
            return date?.let { outputFormat.format(it) }
        }
    }

    object FriendsDiffCallback : DiffUtil.ItemCallback<Friends>() {
        override fun areItemsTheSame(oldItem: Friends, newItem: Friends): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Friends, newItem: Friends): Boolean {
            return oldItem == newItem
        }
    }

    private companion object {
        const val ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val SIMPLE_FORMAT = "dd MMM yyyy"
    }
}