package com.sukhralia.sampleapparchitectureui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sukhralia.sampleapparchitectureui.R
import com.sukhralia.sampleapparchitectureui.database.Person

class PersonAdapter : RecyclerView.Adapter<ViewHolder>() {

    lateinit var mContext: Context
    var data = listOf<Person>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position].name
        holder.age.text = data[position].age.toString()
    }
}

class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    val name : TextView = itemView.findViewById(R.id.name)
    val age : TextView = itemView.findViewById(R.id.age)

}