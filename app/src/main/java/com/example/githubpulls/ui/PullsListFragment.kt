package com.example.githubpulls.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubpulls.GithubPullsApplication
import com.example.githubpulls.databinding.FragmentPullsListBinding
import com.example.githubpulls.viewmodels.PullsListViewModel
import com.example.githubpulls.viewmodels.PullsListViewModelFactory
import com.example.githubpulls.adapters.PullsListingAdapter
import com.example.githubpulls.data.Result
import com.example.githubpulls.extensions.Extensions.setVisibility

class PullsListFragment : Fragment() {

    private var _binding: FragmentPullsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var pullsListingAdapter: PullsListingAdapter

    private val viewModel: PullsListViewModel by viewModels {
        PullsListViewModelFactory((activity?.application as GithubPullsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPullsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pullsListingAdapter = PullsListingAdapter()
        binding.btnRetry.setOnClickListener {
            callApiAndSetData()
        }
        if (false == activity?.isFinishing) {
            setUpRecyclerView()
            callApiAndSetData()
            observeDataAndUpdateUI()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun callApiAndSetData() {
        viewModel.getPullsList()
    }

    private fun setUpRecyclerView() {
        with(binding.rvPullsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = pullsListingAdapter
        }
    }

    private fun observeDataAndUpdateUI() {
        viewModel.getData().observe(viewLifecycleOwner, {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    with(binding) {
                        rvPullsList.setVisibility(true)
                        rvPullsList.setVisibility(true)
                        progress.setVisibility(false)
                        error.setVisibility(false)
                        btnRetry.setVisibility(false)
                        it.data?.let { list -> pullsListingAdapter.updateData(list) }
                    }
                }
                Result.Status.ERROR -> {
                    with(binding) {
                        rvPullsList.setVisibility(false)
                        progress.setVisibility(false)
                        error.setVisibility(true)
                        btnRetry.setVisibility(true)
                    }
                }
                Result.Status.LOADING -> {
                    with(binding) {
                        rvPullsList.setVisibility(false)
                        progress.setVisibility(true)
                        error.setVisibility(false)
                        btnRetry.setVisibility(false)
                    }
                }
            }

        })
    }
}