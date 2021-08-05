package com.example.githubpulls.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubpulls.R
import com.example.githubpulls.data.Pull
import com.example.githubpulls.databinding.PullsListItemBinding

class PullListVH(private val binding: PullsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pull: Pull) {
        with(binding) {
            name.text = pull.title
            tvUserName.text = pull.user.login
            tvCreatedOn.text = pull.created_at.substring(0, 10)
            tvClosedOn.text = pull.closed_at.substring(0, 10)
            Glide.with(itemView)
                .load(pull.user.avatar_url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(avatar)
        }

    }
}