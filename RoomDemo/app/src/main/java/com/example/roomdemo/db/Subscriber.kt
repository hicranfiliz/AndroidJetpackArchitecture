package com.example.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// this way we create an Entity class for a room database
@Entity(tableName = "subscriber_data_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id_column")
    val id : Int,
    @ColumnInfo(name = "subscriber_name_column")
    var name : String,
    @ColumnInfo(name = "subscriber_email")
    var email : String
)