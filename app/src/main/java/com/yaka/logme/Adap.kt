package com.yaka.logme

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list.view.*

class Adap(arrayList: ArrayList<String>): RecyclerView.Adapter<Viewhldr>(){

    val str = arrayList

    override fun getItemCount(): Int {
        return str.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewhldr {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellforRow = layoutInflater.inflate(R.layout.list,parent,false)
        return Viewhldr(cellforRow)    }
    override fun onBindViewHolder(holder: Viewhldr, position: Int) {
        val strr = str.get(position)
        holder?.view?.textView2?.text = strr
    }
}

class Viewhldr(val view: View): RecyclerView.ViewHolder(view){}