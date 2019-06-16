package  com.example.sampleProject.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sampleProject.R
import org.w3c.dom.Text

class FocusAdapter constructor(context: Context, items : List<String>) : RecyclerView.Adapter<FocusViewHolder>(){

    private var context : Context = context
    private var items : List<String> = items
    private lateinit var mListner : RecyclerViewClickListener

    interface RecyclerViewClickListener{
        fun onFocusButtonClicked(pos : Int)
    }

    fun setOnClickListener(listener : RecyclerViewClickListener){
        mListner = listener
    }

    override fun onBindViewHolder(holder: FocusViewHolder, position: Int) {
        holder?.bind(items[position], context)

        if(mListner != null) {
            holder.focusButton.setOnClickListener (object : View.OnClickListener {
                override fun onClick(v: View?) {
                    mListner.onFocusButtonClicked(position)
                }

            })
        }

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

    var editTextView = itemView?.findViewById<TextView>(R.id.EditTextFocus)
    var focusButton = itemView?.findViewById<Button>(R.id.focusButton)

    fun bind(line: String, context: Context){
        editTextView?.text = line
    }
}
