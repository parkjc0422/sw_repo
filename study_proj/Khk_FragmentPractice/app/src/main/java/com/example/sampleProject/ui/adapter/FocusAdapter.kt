package com.example.sampleProject.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.sampleProject.R

class FocusAdapter constructor(context: Context, items : List<String>) : RecyclerView.Adapter<FocusViewHolder>(){

    private var context : Context = context
    private var items : List<String> = items

    override fun onBindViewHolder(holder: FocusViewHolder, position: Int) {
        holder?.bind(items[position], context)

        val itemData = items[position]`
        holder.itemView.setOnClickListener { View.OnClickListener {
            Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        }}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FocusViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_focus, parent, false)
        return FocusViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size
}

/**
 * item view에 set하는 역할.
 * @author khk
 * */
class FocusViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
    private var item = itemView?.findViewById<TextView>(R.id.EditTextFocus)

    fun bind(line: String, context: Context){
        item?.text = line
    }
}
