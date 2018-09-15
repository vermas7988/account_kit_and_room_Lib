package com.yaka.logme

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class ListEntity(
        @PrimaryKey(autoGenerate = true)
        val uid:Long,
        val task:String
)