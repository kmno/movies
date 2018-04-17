package com.movies.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.movies.R
import com.movies.databinding.ActivityMainBinding
import com.movies.model.Model
import com.movies.network.ApiService
import com.movies.viewModel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class Info @Inject constructor() {
    val text = "Hello Dagger 2"
}

class MainActivity : DaggerAppCompatActivity(),
        moviesAdapter.OnItemClickListener {

    @Inject
    lateinit var service: ApiService

    //@Inject
    //lateinit var sharedPref: SharedPreferences

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

/*    private val netCall by lazy {
        ApiInterface.create()
    }*/

    private val adapter = moviesAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        injectDependencies()

        //set viewmodel
       //val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

       // viewModel.loadMovies()
        viewModel.loadMovies(service)
       // viewModel.loadMovies(netCall)

        binding.moviesList.layoutManager = LinearLayoutManager(this)
        binding.moviesList.adapter = adapter

        viewModel.movies.observe(this, defineObserver())

        //set any type of value in prefs
        //prefs["kami"] = "name"

        //get any value from prefs
        //Log.e("pref", sharedPref["kami"])

    }

    private fun injectDependencies() {
        //(application as MyApplication).component.inject(this)
    }

    private fun defineObserver() : Observer<ArrayList<Model.Movie>> {
        return Observer {
           // Log.e("TAG","Observe shod!")
            it?.let{
                adapter.replaceData(it)
            }
        }
    }

    override fun onItemClick(position: Int) {}
}
