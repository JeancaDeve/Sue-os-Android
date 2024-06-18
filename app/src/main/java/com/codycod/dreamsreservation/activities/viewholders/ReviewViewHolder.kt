package com.codycod.dreamsreservation.activities.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.models.MdReview

class ReviewViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_review, viewGroup,false)) {

    //find elements of item
    private val commentItem = itemView.findViewById<TextView>(R.id.tv_comment_rev)
    private val userItem = itemView.findViewById<TextView>(R.id.tv_user_rev)
    private val dateItem = itemView.findViewById<TextView>(R.id.tv_datetime_rev)


    fun bind(review: MdReview) {
        commentItem.text = review.comment
        userItem.text = "${review.user.name} ${review.user.lastname}"
        dateItem.text = review.datetime
    }
}