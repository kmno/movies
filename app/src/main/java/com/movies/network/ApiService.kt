package com.movies.network

import com.movies.model.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top")
    fun getTopMovies(@Query("token") token : String) : Observable<Model.Response>

}