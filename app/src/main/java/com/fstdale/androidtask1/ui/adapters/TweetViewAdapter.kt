package com.fstdale.androidtask1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.databinding.LayoutTweetBinding
import com.fstdale.androidtask1.utils.Constant

class TweetViewAdapter(
    private val userTweets: ArrayList<Tweet>
) : RecyclerView.Adapter<TweetViewAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutTweetBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val tweet = userTweets[position]
        holder.binding.model = Tweet(
            tweet.id,
            Constant.TWITTER_ARTIST,
            tweet.text,
            tweet.created_at)
    }

    override fun getItemCount(): Int {
        return userTweets.size
    }

    class BindingHolder(var binding: LayoutTweetBinding) :
        RecyclerView.ViewHolder(binding.root)
}