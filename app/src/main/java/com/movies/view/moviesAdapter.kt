package com.movies.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.databinding.ListItemBinding
import com.movies.model.Model

class moviesAdapter(private var items: ArrayList<Model.Movie>,
                    private var listener: OnItemClickListener) : RecyclerView.Adapter<moviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun replaceData(arrayList: ArrayList<Model.Movie>) {
        items = arrayList
        Log.e("ADAPTER", "replace date")
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Model.Movie, listener: OnItemClickListener) {

                binding.movie = movie

                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })

                binding.executePendingBindings()

        }
    }
}