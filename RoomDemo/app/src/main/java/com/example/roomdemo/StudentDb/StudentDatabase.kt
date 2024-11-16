package com.example.roomdemo.StudentDb

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Student::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4, spec = StudentDatabase.Migration3To4::class),
        AutoMigration(from = 4, to = 5, spec = StudentDatabase.Migration4To5::class),
    ])
abstract class StudentDatabase : RoomDatabase(){

    abstract val studentDao : StudentDAO

    @RenameColumn(tableName = "student_data_table", "student_course_name", "subject_name")
    class Migration3To4 : AutoMigrationSpec

    @DeleteColumn(tableName = "student_data_table", "student_mail")
    class Migration4To5 : AutoMigrationSpec

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
//                    val MIGRATION_1_2 = object : Migration(2, 3) {
//                        override fun migrate(database: SupportSQLiteDatabase) {
//                            database.execSQL(
//                                "ALTER TABLE student_data_table ADD COLUMN student_course_name TEXT"
//                            )
//                        }
//                    }
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    )
                        //.addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}