package com.example.sampleProject.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sampleProject.R

class CustomAdapter constructor(context: Context, items : List<String>) : RecyclerView.Adapter<CustomViewHolder>(){

    private var context : Context = context
    private var items : List<String> = items

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder?.bind(items[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}

/**
 * item view에 set하는 역할.
 * @author khk
 * */
class CustomViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
    var item = itemView?.findViewById<TextView>(R.id.lineStringTv)

    fun bind(line: String, context: Context){
        item?.text = line
    }
}
