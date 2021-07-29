package com.emre.b4abasicquerieskotlin

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import java.util.*

class ResultAdapter(context: Context?, list: List<ParseObject>?) : RecyclerView.Adapter<ResultHolder>() {

    private var context: Context? = null
    private var list: List<ParseObject>? = null

    init {

        this.list = list
        this.context = context
    }

    fun clearList() {
        list = ArrayList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.result_cell, parent, false)
        return ResultHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val profile = list!![position]
        holder.name?.text = profile.getString("name")
        holder.details?.text = "Friend Count: " + profile.getInt("friendCount") + " Birthday: " + profile.getDate(
            "birthDay"
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}

class ResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name: TextView? = itemView.findViewById(R.id.name)
    var details: TextView? = itemView.findViewById(R.id.details)


}