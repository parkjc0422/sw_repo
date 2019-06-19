package  com.example.sampleProject.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sampleProject.R

class FocusAdapter constructor(context: Context, items : List<String>) : RecyclerView.Adapter<FocusViewHolder>(){

    private var items : List<String> = items
    private var context = context

    override fun onBindViewHolder(holder: FocusViewHolder, position: Int) {

        holder.focusButton.setOnClickListener {

            /**
             * wrong
             * */
            //View.OnClickListener { Toast.makeText(context,"$position : " +  holder.editTextView.text, Toast.LENGTH_SHORT).show() }
            it.setOnClickListener { Toast.makeText(context,"$position : " +  holder.editTextView.text, Toast.LENGTH_SHORT).show()}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FocusViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_focus, parent, false)
        return FocusViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

}

/**
 * item에 대한 view를 set하는 역할.
 * @author khk
 * */
class FocusViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){

    var editTextView: EditText = itemView?.findViewById(R.id.EditTextFocus)
    var focusButton: Button = itemView?.findViewById(R.id.focusButton)

}
