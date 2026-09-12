package com.academicservice.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "lectures",
    foreignKeys = [
        ForeignKey(
            entity = Course::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Lecture(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val courseId: Int,
    val lectureDate: Long,
    val lectureTime: String, // بصيغة HH:mm
    val duration: Int = 60, // المدة بالدقائق
    val topic: String = "",
    val location: String = "",
    val isOnline: Boolean = false,
    val notes: String = ""
)
