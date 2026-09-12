package com.academicservice.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.academicservice.data.database.AppDatabase
import com.academicservice.data.models.FinancialRecord
import com.academicservice.data.repository.FinancialRepository
import kotlinx.coroutines.launch

class FinancialViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = FinancialRepository(database.financialRecordDao())
    
    val totalPayments: LiveData<Double> = repository.getTotalPayments()
    
    fun getRecordsByStudent(studentId: Int): LiveData<List<FinancialRecord>> {
        return repository.getRecordsByStudent(studentId)
    }
    
    fun getTotalPaymentByStudent(studentId: Int): LiveData<Double> {
        return repository.getTotalPaymentByStudent(studentId)
    }
    
    fun getRecordsByDateRange(startDate: Long, endDate: Long): LiveData<List<FinancialRecord>> {
        return repository.getRecordsByDateRange(startDate, endDate)
    }
    
    fun getRecordCountByStudent(studentId: Int): LiveData<Int> {
        return repository.getRecordCountByStudent(studentId)
    }
    
    fun insertRecord(record: FinancialRecord) {
        viewModelScope.launch {
            repository.insertRecord(record)
        }
    }
    
    fun updateRecord(record: FinancialRecord) {
        viewModelScope.launch {
            repository.updateRecord(record)
        }
    }
    
    fun deleteRecord(record: FinancialRecord) {
        viewModelScope.launch {
            repository.deleteRecord(record)
        }
    }
}
