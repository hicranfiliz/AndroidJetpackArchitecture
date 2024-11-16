package com.example.roomdemo.db

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Subscriber::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = SubscriberDatabase.Migration1To2::class),
        AutoMigration(from = 2, to = 3, spec = SubscriberDatabase.Migration2To3::class)
    ])
abstract class SubscriberDatabase: RoomDatabase() {
    abstract val subscriberDAO : SubscriberDAO

    @RenameColumn("subscriber_data_table", "subscriber_id", "subscriber_id_column")
    class Migration1To2 : AutoMigrationSpec

    @RenameColumn("subscriber_data_table", "subscriber_name", "subscriber_name_column")
    class Migration2To3 : AutoMigrationSpec

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}