package com.academicservice.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "financial_records",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FinancialRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentId: Int,
    val description: String,
    val amount: Double,
    val transactionType: TransactionType = TransactionType.PAYMENT, // دفعة أو تعديل
    val transactionDate: Long = System.currentTimeMillis(),
    val notes: String = ""
)

enum class TransactionType {
    PAYMENT,        // دفعة
    ADJUSTMENT,     // تعديل
    REFUND,         // استرجاع
    INITIAL_AMOUNT  // المبلغ الأولي
}
