package com.example.studentbeans.fragments.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentbeans.model.PostsModel
import com.example.studentbeans.R
import com.squareup.picasso.Picasso

class PostsRecyclerAdapter(private val photos: List<PostsModel>): RecyclerView.Adapter<PostsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostsRecyclerAdapter.ViewHolder, position: Int) {
        val photoCard = photos[position]

        holder.itemTitle.text = photoCard.title
        Picasso.get().load(photoCard.thumbnailUrl).into(holder.itemImage) // Load the thumbnail from the url
        holder.itemImage.clipToOutline = true // makes the imageView have rounded edges (also needs specific drawable background)
    }

    override fun getItemCount(): Int {
        return photos.size // How many posts we've got
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //Only need these two for the posts
        var itemImage: ImageView
        var itemTitle: TextView

        init{ // Have to be initialised
            itemImage = itemView.findViewById(R.id.card_image)
            itemTitle = itemView.findViewById(R.id.card_title)
        }
    }
}