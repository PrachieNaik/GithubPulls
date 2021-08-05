package com.example.githubpulls.repositories

import com.example.githubpulls.BaseApiSource
import com.example.githubpulls.network.PullsListApi


class PullsListRepo(private val pullsListApi: PullsListApi) : BaseApiSource() {

    suspend fun getPullsList() = getResult {
        pullsListApi.getPullsList()
    }
}