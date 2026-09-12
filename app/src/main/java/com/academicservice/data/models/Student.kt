package com.academicservice.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val universityId: String,
    val password: String,
    val specialty: String,
    val level: String, // مثل: الأول، الثاني، الثالث، الرابع
    val phoneNumber: String,
    val totalAmount: Double = 0.0,
    val paidAmount: Double = 0.0,
    val remainingAmount: Double = 0.0,
    val createdDate: Long = System.currentTimeMillis(),
    val notes: String = ""
) {
    fun getRemainingAmount(): Double {
        return totalAmount - paidAmount
    }

    fun getPaymentPercentage(): Float {
        return if (totalAmount > 0) {
            (paidAmount / totalAmount * 100).toFloat()
        } else {
            0f
        }
    }
}
