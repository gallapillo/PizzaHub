package com.gallapillo.pizzahub.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gallapillo.pizzahub.R
import com.gallapillo.pizzahub.model.Pizza

class MainListAdapter(value: ArrayList<Pizza>?) : RecyclerView.Adapter<MainListAdapter.ViewHolder>(){

    private var pizzas : ArrayList<Pizza> = ArrayList<Pizza>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Name: TextView = view.findViewById(R.id.pizza_text_view_name)
        val Price: TextView = view.findViewById(R.id.pizza_text_view_price)
        val Image_URL: ImageView = view.findViewById(R.id.pizza_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pizza_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.tag = pizzas[position]
        holder.Name.text = pizzas[position].Name
    }

    override fun getItemCount(): Int = pizzas.size

}