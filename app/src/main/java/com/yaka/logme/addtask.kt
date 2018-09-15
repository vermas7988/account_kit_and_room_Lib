package com.yaka.logme

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_addtask.*

class addtask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtask)
    }
    fun addit(view: View){
        addPerson(editText.text.toString())
        var intent: Intent = Intent(this,AccountActivity::class.java)
        startActivity(intent)
    }
    fun addPerson(task: String) {
        val ListEntity = ListEntity(0, task)

        Single.fromCallable {
            AccountActivity.database?.personDao()?.insert(ListEntity)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

}
