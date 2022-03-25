package com.fstdale.androidtask1.ui.pages.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.FragmentOthersBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class OthersFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val factory : OthersViewModelFactory by instance()
    private lateinit var binding: FragmentOthersBinding
    private lateinit var othersViewModel: OthersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.others)
        othersViewModel = ViewModelProvider(this, factory).get(OthersViewModel::class.java)
        binding = FragmentOthersBinding.inflate(inflater, container, false).apply {
            viewModel = othersViewModel
            lifecycleOwner = this@OthersFragment
        }
        othersViewModel.getUserDetails()
        return binding.root
    }
}