package com.example.roomdemo.StudentDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_data_table")
data class Student (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val id : Int,

    @ColumnInfo(name = "student_name")
    val name : String,

//    @ColumnInfo(name = "student_mail", defaultValue = "No Email")
//    var email : String,

    @ColumnInfo(name = "subject_name")
    var courseName : String?
)