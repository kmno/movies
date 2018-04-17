package com.movies.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.movies.model.Model
import com.movies.model.Repository
import com.movies.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(var repository: Repository) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val isLoading = ObservableField(false)
    var movies = MutableLiveData<ArrayList<Model.Movie>>()//Model.ListResponse>()


    fun loadMovies(netCall: ApiService) {

        isLoading.set(true)
        Log.e("MOVIES", "is loading ...")

        val disposable =
                repository
                .getMoviesList(netCall)
                //netCall.getTopMovies("2880597e-7192-46aa-b5d3-d7cb5dd5d116")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                /*.subscribe(
                { result ->
                   // Log.e("RESULT", result.data.movies)
                   // txt_search_result.text = "${result.query.searchinfo.totalhits} result found"
                },
                { error -> Log.e("Error", error.message)

                    //Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
        )*/
                .subscribeWith(object : DisposableObserver<Model.Response>() {

                    override fun onError(e: Throwable) {
                        //todo
                    }

                    override fun onNext(result: Model.Response) {
                        movies.value = ArrayList(result.data.movies)
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }

                })
        compositeDisposable.add(disposable)
    }

    @Override
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}