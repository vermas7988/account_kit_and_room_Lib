package com.yaka.logme

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.list.*

class AccountActivity : AppCompatActivity() {
    companion object {
        var database: MyDatabase? = null
        var id:Long = 1
        var boolean = true
        var array:ArrayList<String> = ArrayList()
        var x:Int = 0
        var mm:Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        textView.setText("Only I can change my life. No one can do it for me.\"\t\t\t-Carol Burnett")
        //db
        AccountActivity.database =  Room.databaseBuilder(this, MyDatabase::class.java, "database1").build()
        regi()
        //recyclerView
        var recyclerView = findViewById(R.id.recycle) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adap(array)


    }

    fun addtsk(view:View){
        var intent: Intent = Intent(this,addtask::class.java)
        startActivity(intent)

    }
    fun regi() {
        AccountActivity.database?.personDao()?.getAllPeople()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { result ->

                    for(i in result.indices) {
                        if (x<=result.size){
                        array.add(result[x++].task)}
                    }
                }


    }

    //write a function to delete item from recycler and call below fn to remove from db



}
