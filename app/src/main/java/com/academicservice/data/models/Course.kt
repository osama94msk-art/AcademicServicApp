package com.academicservice.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "courses",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentId: Int,
    val courseName: String,
    val courseCode: String,
    val instructor: String = "",
    val creditHours: Int = 3,
    val semester: String = "", // مثل: الفصل الأول، الفصل الثاني
    val scheduleTime: String = "",
    val room: String = "",
    val notes: String = ""
)
