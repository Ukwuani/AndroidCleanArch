package com.echwood.androidcleanarch.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.echwood.androidcleanarch.R
import com.echwood.androidcleanarch.models.CityListData
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
    private var context: Context,
    private var data: List<CityListData>,
    private var listener: OnItemClickListener):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, null)
        view.layoutParams =
            RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.click(data[position], listener)
        holder.tvCity.text = data[position].name
        holder.tvDesc.text = data[position].description

        val images: String = data[position].background

        Glide.with(context)
                .load(images)
                .into(holder.background)
    }



    override fun getItemCount(): Int {
        return data.size
    }


    interface OnItemClickListener {
        fun onClick(Item: CityListData)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCity = itemView.city!!
        var tvDesc = itemView.hotel!!
        var background = itemView.image!!


        fun click( cityListData: CityListData, listener: OnItemClickListener) {
            itemView.setOnClickListener{
                    listener.onClick(cityListData)
            }
        }
    }


}
