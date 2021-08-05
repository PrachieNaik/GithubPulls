package com.example.githubpulls.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.githubpulls.databinding.PullsListItemBinding
import com.example.githubpulls.data.Pull
import com.example.githubpulls.viewholders.PullListVH

class PullsListingAdapter : RecyclerView.Adapter<PullListVH>() {

    private val pullsList = mutableListOf<Pull>()

    fun updateData(list: List<Pull>) {
        val temp = pullsList.size
        pullsList.addAll(list)
        notifyItemRangeChanged(temp, list.size)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): PullListVH {
        val binding = PullsListItemBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PullListVH(binding)
    }

    override fun onBindViewHolder(holder: PullListVH, position: Int) {
        holder.bind(pullsList[position])
    }

    override fun getItemCount(): Int = pullsList.size

}

