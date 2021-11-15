package com.example.whistleapp.serverRepo

import com.example.whistleapp.serverRepo.data.IssuesResult
import io.reactivex.Single
import retrofit2.http.*

interface GitHubService {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/repos/ReactiveX/RxJava/issues")
    fun getIssues(@Query("filter") filter :String = "repos") : Single<IssuesResult>


}