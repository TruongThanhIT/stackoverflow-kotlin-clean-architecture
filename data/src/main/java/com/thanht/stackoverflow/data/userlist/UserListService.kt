package com.thanht.stackoverflow.data.userlist

import com.thanht.stackoverflow.data.responses.UserListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserListService {
    @GET("2.2/users")
    fun getUsers(@Query("page") page: Int,
                            @Query("pagesize") pageSize: Int,
                            @Query("site") site: String): Observable<UserListResponse>
}