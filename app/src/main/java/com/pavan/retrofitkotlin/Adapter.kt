package com.pavan.retrofitkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavan.retrofitkotlin.models.UsersItem
import retrofit2.Response

class Adapter(var context:Context,var arraylist: List<UsersItem> ):
    RecyclerView.Adapter<Adapter.viewholder>() {

    inner class viewholder(v: View) : RecyclerView.ViewHolder(v) {
         var img = v.findViewById<ImageView>(R.id.profileimage)
        var tv = v.findViewById<TextView>(R.id.tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
      val view = LayoutInflater.from(context).inflate(R.layout.useritem,parent,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
     return arraylist.count()
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       Glide.with(context).load(arraylist[position].avatar_url).into(holder.img)
        holder.tv.text = arraylist[position].login
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatedata(data: List<UsersItem>) {
        arraylist = data
        notifyDataSetChanged()

    }
}