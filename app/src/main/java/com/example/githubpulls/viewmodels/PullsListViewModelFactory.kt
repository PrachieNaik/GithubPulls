package com.example.githubpulls.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubpulls.repositories.PullsListRepo

class PullsListViewModelFactory(private val repository: PullsListRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PullsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PullsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}