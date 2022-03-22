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
    private val feedsViewModel: FeedsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedsBinding.inflate(inflater, container, false).apply {
            viewModel = feedsViewModel
            lifecycleOwner = this@FeedsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        feedsViewModel.getTweetList()
        binding.refresh.setOnRefreshListener {
            feedsViewModel.getTweetList()
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
        feedsViewModel.data.observe(viewLifecycleOwner, userListUpdateObserver)
    }
}