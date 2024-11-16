package com.example.roomdemo.StudentDb

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2)])
abstract class StudentDatabase : RoomDatabase(){

    abstract val studentDao : StudentDAO

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val MIGRATION_1_2 = object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL(
                                "ALTER TABLE student_data_table ADD COLUMN student_mail TEXT NOT NULL DEFAULT 'No Email'"
                            )
                        }
                    }
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE student_data_table ADD COLUMN student_mail TEXT NOT NULL DEFAULT 'No Email'"
            )
        }
    }

}