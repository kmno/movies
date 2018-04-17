package com.movies.model

import com.movies.network.ApiService
import com.movies.utils.NetManager
import io.reactivex.Observable
import javax.inject.Inject


class Repository @Inject constructor(var netManager: NetManager) {

    val localDataSource = Local()
    val remoteDataSource = Remote()

    fun getMoviesList(netCall: ApiService) : Observable<Model.Response> {

       /* netManager.isConnectedToInternet?.let {
            if (it) {*/
                return remoteDataSource.getList(netCall)
        /*}
        return localDataSource.getList()*/

    }
}
