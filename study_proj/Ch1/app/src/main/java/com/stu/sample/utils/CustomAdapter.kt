package com.stu.sample.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater


abstract class CustomAdapter: RecyclerView.Adapter<CustomViewHolder> (){
    var items:MutableList<CustomAdapterItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CustomViewHolder(inflater.inflate(layoutId(viewType), parent, false))
    }

    abstract fun layoutId(viewType: Int): Int

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        items[position].let {
            holder.bindData(it)
        }
    }
}

open class CustomAdapterItem ()

open class CustomViewHolder(view:View): RecyclerView.ViewHolder(view){
    fun bindData(item: CustomAdapterItem) {}
}