package com.example.githubpulls.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubpulls.data.Pull
import com.example.githubpulls.repositories.PullsListRepo
import kotlinx.coroutines.launch
import com.example.githubpulls.data.Result

class PullsListViewModel(private val repository: PullsListRepo) : ViewModel() {

    private val data: MutableLiveData<Result<List<Pull>>> =
        MutableLiveData<Result<List<Pull>>>()

    fun getPullsList() {
        viewModelScope.launch {
            data.value = Result.loading()
            data.value = repository.getPullsList()
        }
    }

    fun getData(): LiveData<Result<List<Pull>>> = data
}