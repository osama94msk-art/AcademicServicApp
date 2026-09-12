package com.academicservice.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "assignments",
    foreignKeys = [
        ForeignKey(
            entity = Course::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Assignment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val courseId: Int,
    val assignmentName: String,
    val description: String = "",
    val dueDate: Long, // تاريخ الاستحقاق
    val dueTime: String = "23:59", // وقت التسليم
    val status: AssignmentStatus = AssignmentStatus.PENDING, // قيد التنفيذ، مكتمل، متأخر
    val submissionDate: Long? = null, // تاريخ التسليم الفعلي
    val score: Int? = null, // الدرجة
    val maxScore: Int? = null,
    val notes: String = "",
    val isUndefinedDate: Boolean = false // في حالة عدم معرفة الموعد الدقيق
)

enum class AssignmentStatus {
    PENDING,      // قيد التنفيذ
    COMPLETED,    // مكتمل
    LATE,         // متأخر
    SUBMITTED     // تم التسليم
}

fun Assignment.getPriorityLevel(): PriorityLevel {
    return when {
        isUndefinedDate -> PriorityLevel.LOW
        status == AssignmentStatus.LATE -> PriorityLevel.CRITICAL
        System.currentTimeMillis() > dueDate -> PriorityLevel.CRITICAL
        (dueDate - System.currentTimeMillis()) <= 48 * 60 * 60 * 1000 -> PriorityLevel.HIGH
        (dueDate - System.currentTimeMillis()) <= 7 * 24 * 60 * 60 * 1000 -> PriorityLevel.MEDIUM
        else -> PriorityLevel.LOW
    }
}

enum class PriorityLevel {
    CRITICAL,   // أحمر
    HIGH,       // برتقالي
    MEDIUM,     // أصفر
    LOW         // أخضر
}
