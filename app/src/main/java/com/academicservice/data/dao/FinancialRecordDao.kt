package com.academicservice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.academicservice.data.models.FinancialRecord

@Dao
interface FinancialRecordDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: FinancialRecord): Long
    
    @Update
    suspend fun updateRecord(record: FinancialRecord)
    
    @Delete
    suspend fun deleteRecord(record: FinancialRecord)
    
    @Query("SELECT * FROM financial_records WHERE id = :id")
    fun getRecordById(id: Int): LiveData<FinancialRecord>
    
    @Query("SELECT * FROM financial_records WHERE studentId = :studentId ORDER BY transactionDate DESC")
    fun getRecordsByStudent(studentId: Int): LiveData<List<FinancialRecord>>
    
    @Query("SELECT SUM(amount) FROM financial_records WHERE studentId = :studentId AND transactionType = 'PAYMENT'")
    fun getTotalPaymentByStudent(studentId: Int): LiveData<Double>
    
    @Query("""
        SELECT SUM(amount) FROM financial_records 
        WHERE transactionType = 'PAYMENT'
    """)
    fun getTotalPayments(): LiveData<Double>
    
    @Query("""
        SELECT * FROM financial_records 
        WHERE transactionDate >= :startDate AND transactionDate <= :endDate
        ORDER BY transactionDate DESC
    """)
    fun getRecordsByDateRange(startDate: Long, endDate: Long): LiveData<List<FinancialRecord>>
    
    @Query("SELECT COUNT(*) FROM financial_records WHERE studentId = :studentId")
    fun getRecordCountByStudent(studentId: Int): LiveData<Int>
}
