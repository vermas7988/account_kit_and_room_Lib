package com.yaka.logme

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list.view.*

class Adap(arrayList: ArrayList<String>): RecyclerView.Adapter<Viewhldr>(){

    var str = arrayList

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
        holder?.view.button2.setOnClickListener {
            deldata(str.get(position))
            str.remove(str[position])
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, str.size)
            holder.itemView.setVisibility(View.GONE)

        }


    }

    fun deldata(task:String){
        AccountActivity.mm++
        Single.fromCallable {
            AccountActivity.database?.personDao()?.DelAll(task)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }
}

class Viewhldr(val view: View): RecyclerView.ViewHolder(view){
    init {

    }
}