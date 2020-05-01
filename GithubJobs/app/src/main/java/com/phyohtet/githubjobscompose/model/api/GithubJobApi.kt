package com.phyohtet.githubjobscompose.model.api

import com.phyohtet.githubjobscompose.model.dto.JobPositionDTO
import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubJobApi {

    @GET("positions.json")
    fun findPositions(
            @Query("description")
            description: String,
            @Query("location")
            location: String,
            @Query("page")
            page: Int
    ): Observable<List<JobPositionDTO>>

    @GET("positions.json")
    fun findPositions(
            @Query("description")
            description: String,
            @Query("location")
            location: String,
            @Query("full_time")
            fullTime: Boolean,
            @Query("page")
            page: Int
    ): Observable<List<JobPositionDTO>>

    @GET("positions/{id}")
    fun getPosition(@Path("id") id: String): Maybe<JobPositionDTO>

}