package com.yaka.logme

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ListDao {
    @Query("SELECT * FROM listEntity")
    fun getAllPeople(): Flowable<List<ListEntity>>

    @Insert
    fun insert(person: ListEntity)

    @Query
    ("DELETE FROM listEntity WHERE task == :task")
    fun DelAll(task:String)
}