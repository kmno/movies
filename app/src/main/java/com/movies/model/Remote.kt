package com.movies.model

import com.movies.network.ApiService
import io.reactivex.Observable

class Remote {
    fun getList(netCall: ApiService): Observable<Model.Response> {
        return netCall.getTopMovies("2880597e-7192-46aa-b5d3-d7cb5dd5d116")
    }
}