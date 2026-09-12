package com.academicservice.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.academicservice.data.dao.*
import com.academicservice.data.models.*

@Database(
    entities = [
        Student::class,
        Course::class,
        Assignment::class,
        FinancialRecord::class,
        Lecture::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun assignmentDao(): AssignmentDao
    abstract fun financialRecordDao(): FinancialRecordDao
    abstract fun lectureDao(): LectureDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "academic_service_db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
