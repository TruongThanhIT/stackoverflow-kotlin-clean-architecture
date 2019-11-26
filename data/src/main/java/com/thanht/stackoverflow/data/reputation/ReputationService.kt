package com.thanht.stackoverflow.data.reputation

import com.thanht.stackoverflow.data.responses.ReputationListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReputationService {
    @GET("2.2/users/{user_id}/reputation-history")
    fun getReputationList(
            @Path("user_id") userId: Int,
            @Query("page") page: Int,
            @Query("pagesize") pageSize: Int,
            @Query("site") site: String): Observable<ReputationListResponse>

}