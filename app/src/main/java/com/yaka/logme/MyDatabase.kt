package com.yaka.logme

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(ListEntity::class), version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun personDao(): ListDao

}