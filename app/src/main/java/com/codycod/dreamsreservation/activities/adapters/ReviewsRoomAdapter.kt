package com.codycod.dreamsreservation.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.activities.viewholders.ReviewViewHolder
import com.codycod.dreamsreservation.models.MdReview

class ReviewsRoomAdapter(private val listReviews: ArrayList<MdReview>) :
    RecyclerView.Adapter<ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int = listReviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listReviews[position])
    }

}