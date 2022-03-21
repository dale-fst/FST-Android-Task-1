package com.fstdale.androidtask1.ui.pages.feeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.databinding.FragmentFeedsBinding
import com.fstdale.androidtask1.ui.adapters.TweetViewAdapter

class FeedsFragment : Fragment() {

    private lateinit var binding: FragmentFeedsBinding
    private val feedViewModel: FeedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedsBinding.inflate(inflater, container, false).apply {
            viewModel = feedViewModel
            lifecycleOwner = this@FeedsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        feedViewModel.getTweetList()
        binding.refresh.setOnRefreshListener {
            feedViewModel.getTweetList()
            binding.refresh.isRefreshing = false
        }
        val userListUpdateObserver: Observer<ArrayList<Tweet>> =
            Observer<ArrayList<Tweet>> { list ->
                val recyclerViewAdapter = TweetViewAdapter(list)
                binding.list.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = recyclerViewAdapter
                }
            }
        feedViewModel.data.observe(viewLifecycleOwner, userListUpdateObserver)
    }
}