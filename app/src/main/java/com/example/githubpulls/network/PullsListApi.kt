package com.example.githubpulls.network

import com.example.githubpulls.data.Pull
import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Query

interface PullsListApi {

    @GET("repos/jainaman224/Algo_Ds_Notes/pulls")
    suspend fun getPullsList(
        @Query("state") state: String = "closed"
    ): Response<List<Pull>>

}