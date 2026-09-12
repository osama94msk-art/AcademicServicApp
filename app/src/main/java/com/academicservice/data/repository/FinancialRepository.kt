package com.academicservice.data.repository

import androidx.lifecycle.LiveData
import com.academicservice.data.dao.FinancialRecordDao
import com.academicservice.data.models.FinancialRecord

class FinancialRepository(private val financialRecordDao: FinancialRecordDao) {
    
    fun getRecordsByStudent(studentId: Int): LiveData<List<FinancialRecord>> {
        return financialRecordDao.getRecordsByStudent(studentId)
    }
    
    fun getTotalPaymentByStudent(studentId: Int): LiveData<Double> {
        return financialRecordDao.getTotalPaymentByStudent(studentId)
    }
    
    fun getTotalPayments(): LiveData<Double> {
        return financialRecordDao.getTotalPayments()
    }
    
    fun getRecordsByDateRange(startDate: Long, endDate: Long): LiveData<List<FinancialRecord>> {
        return financialRecordDao.getRecordsByDateRange(startDate, endDate)
    }
    
    fun getRecordCountByStudent(studentId: Int): LiveData<Int> {
        return financialRecordDao.getRecordCountByStudent(studentId)
    }
    
    fun getRecordById(id: Int): LiveData<FinancialRecord> {
        return financialRecordDao.getRecordById(id)
    }
    
    suspend fun insertRecord(record: FinancialRecord): Long {
        return financialRecordDao.insertRecord(record)
    }
    
    suspend fun updateRecord(record: FinancialRecord) {
        financialRecordDao.updateRecord(record)
    }
    
    suspend fun deleteRecord(record: FinancialRecord) {
        financialRecordDao.deleteRecord(record)
    }
}
