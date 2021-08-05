package com.example.githubpulls

import android.app.Application
import com.example.githubpulls.network.PullsListApi
import com.example.githubpulls.repositories.PullsListRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GithubPullsApplication : Application() {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var fetchApi: PullsListApi = retrofit.create(PullsListApi::class.java)

    val repository by lazy { PullsListRepo(fetchApi) }
}