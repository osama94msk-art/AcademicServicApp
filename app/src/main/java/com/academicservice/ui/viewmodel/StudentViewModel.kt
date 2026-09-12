package com.academicservice.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.academicservice.data.database.AppDatabase
import com.academicservice.data.models.Student
import com.academicservice.data.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = StudentRepository(
        database.studentDao(),
        database.courseDao(),
        database.assignmentDao(),
        database.financialRecordDao(),
        database.lectureDao()
    )
    
    val allStudents: LiveData<List<Student>> = repository.allStudents
    val studentCount: LiveData<Int> = repository.studentCount
    val totalRevenue: LiveData<Double> = repository.totalRevenue
    val totalPaidAmount: LiveData<Double> = repository.totalPaidAmount
    val totalRemainingAmount: LiveData<Double> = repository.totalRemainingAmount
    
    private var _searchResults: LiveData<List<Student>>? = null
    
    fun insertStudent(student: Student) {
        viewModelScope.launch {
            repository.insertStudent(student)
        }
    }
    
    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }
    
    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.deleteStudent(student)
        }
    }
    
    fun getStudentById(id: Int): LiveData<Student> {
        return repository.getStudentById(id)
    }
    
    fun searchStudents(query: String): LiveData<List<Student>> {
        _searchResults = repository.searchStudents(query)
        return _searchResults!!
    }
    
    fun getStudentsBySpecialty(specialty: String): LiveData<List<Student>> {
        return repository.getStudentsBySpecialty(specialty)
    }
    
    fun getStudentsOrderByDate(): LiveData<List<Student>> {
        return repository.getStudentsOrderByDate()
    }
}
