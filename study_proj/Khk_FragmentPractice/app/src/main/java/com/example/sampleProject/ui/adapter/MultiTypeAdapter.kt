package  com.example.sampleProject.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sampleProject.R

class MultiTypeAdapter constructor(context: Context, items : List<String>) : RecyclerView.Adapter<TypeHolder>(){

    private var items : List<String> = items
    private var context = context

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {

        //holder.itemView.setOnClickListener { it.setOnClickListener { Toast.makeText(context, holder.itemViewType.toString() , Toast.LENGTH_LONG).show() } }
        holder.button.setOnClickListener { it.setOnClickListener { Toast.makeText(context, "type : " + holder.itemViewType.toString(), Toast.LENGTH_SHORT).show() } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        var view: View?

        return when(viewType){

            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_a, parent, false)
                return TypeCHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_focus_a, parent, false)
                TypeAHolder(view)
            }
            else ->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_focus_b, parent, false)
                TypeBHolder(view)
            }

        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {

        return position % 3
    }

}

class TypeAHolder constructor(itemView : View) : TypeHolder(itemView){
    override var button: Button
        get() = itemView?.findViewById(R.id.focusButtonA)
        set(value) {button = value}
    var editText : EditText = itemView?.findViewById(R.id.EditTextFocusA)
    var textView : TextView = itemView?.findViewById(R.id.TextViewFocusA)
}

class TypeBHolder constructor(itemView : View) :  TypeHolder(itemView){
    override var button: Button
        get() = itemView?.findViewById(R.id.focusButtonB)
        set(value) {button = value}
    var editText : EditText = itemView?.findViewById(R.id.EditTextFocusB)
    var textView : TextView = itemView?.findViewById(R.id.TextViewFocusB)
}

class TypeCHolder constructor(itemView : View) :  TypeHolder(itemView){
    override var button: Button
        get() = itemView?.findViewById(R.id.buttonC)
        set(value) {button = value}
    var imageView : ImageView = itemView?.findViewById(R.id.imageViewA)
    var textView : TextView = itemView?.findViewById(R.id.textViewA)
}

abstract class TypeHolder  constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
    internal abstract var button : Button
}