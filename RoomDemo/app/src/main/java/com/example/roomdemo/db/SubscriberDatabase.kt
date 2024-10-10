package com.example.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase: RoomDatabase() {
    abstract val subscriberDAO : SubscriberDAO

    companion object{
        @Volatile
        private var INSTANCE : SubscriberDatabase? = null
            fun getInstance(context: Context) : SubscriberDatabase? {
                synchronized(this){
                    var instance  = INSTANCE
                        if (instance == null){
                            databaseBuilder(
                                context = context.applicationContext,
                                klass = SubscriberDatabase::class.java,
                                name = "subscriber_data_database"
                            ).build()
                            INSTANCE = instance
                        }
                    return instance
                }
            }
    }
}