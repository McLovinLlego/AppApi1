package com.example.appapi1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//Se define la clase MyAdapter que se utiliza como adaptador
//para mostrar una lista de elementos userItem en el recyclerview
class MyAdapter (var con : Context, var list: List<UsersItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    //Contiene una clase interna ViewHolder para mentener la referencia a los
    //elementos de vista de cada elemento de la lista
    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v){
        var img = v.findViewById<ImageView>(R.id.RV_Image)
        var tvName = v.findViewById<TextView>(R.id.RV_tv)
    }
    //el adaptador infla la vista del elemento de la lista en
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.list_item,parent, false)
        return ViewHolder(view)
    }
    //getItemCount devuelve el numero de elementos
    override fun getItemCount(): Int {
        return list.count()
    }
    //los datos se vinculan a la vista en
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(con).load(list[position].avatar_url).into(holder.img)

        holder.tvName.text = list[position].login
    }
}