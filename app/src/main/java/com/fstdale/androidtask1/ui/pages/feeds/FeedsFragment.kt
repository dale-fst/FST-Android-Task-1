package com.fstdale.androidtask1.ui.pages.feeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.databinding.FragmentFeedsBinding
import com.fstdale.androidtask1.ui.adapters.TweetViewAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FeedsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val factory : FeedsViewModelFactory by instance()
    private lateinit var binding: FragmentFeedsBinding
    private lateinit var feedsViewModel: FeedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        feedsViewModel = ViewModelProvider(this, factory).get(FeedsViewModel::class.java)
        binding = FragmentFeedsBinding.inflate(inflater, container, false).apply {
            viewModel = feedsViewModel
            lifecycleOwner = this@FeedsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        feedsViewModel.getTweetList()
        binding.refresh.setOnRefreshListener {
            feedsViewModel.getTweetList(true)
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
        feedsViewModel.tweetList.observe(viewLifecycleOwner, userListUpdateObserver)
    }
}