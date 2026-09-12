package com.academicservice.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.academicservice.data.database.AppDatabase
import com.academicservice.data.models.Assignment
import com.academicservice.data.models.AssignmentStatus
import com.academicservice.data.repository.AssignmentRepository
import kotlinx.coroutines.launch

class AssignmentViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = AssignmentRepository(database.assignmentDao())
    
    val upcomingAssignments: LiveData<List<Assignment>> = repository.getUpcomingAssignments()
    val overdueAssignments: LiveData<List<Assignment>> = repository.getOverdueAssignments()
    val pendingCount: LiveData<Int> = repository.getPendingAssignmentCount()
    val completedCount: LiveData<Int> = repository.getCompletedAssignmentCount()
    
    fun getAssignmentsByStudent(studentId: Int): LiveData<List<Assignment>> {
        return repository.getAssignmentsByStudent(studentId)
    }
    
    fun getAssignmentsByCourse(courseId: Int): LiveData<List<Assignment>> {
        return repository.getAssignmentsByCourse(courseId)
    }
    
    fun getAssignmentById(id: Int): LiveData<Assignment> {
        return repository.getAssignmentById(id)
    }
    
    fun getAssignmentsByStatus(status: AssignmentStatus): LiveData<List<Assignment>> {
        return repository.getAssignmentsByStatus(status)
    }
    
    fun getAssignmentsByDateRange(startDate: Long, endDate: Long): LiveData<List<Assignment>> {
        return repository.getAssignmentsByDateRange(startDate, endDate)
    }
    
    fun insertAssignment(assignment: Assignment) {
        viewModelScope.launch {
            repository.insertAssignment(assignment)
        }
    }
    
    fun updateAssignment(assignment: Assignment) {
        viewModelScope.launch {
            repository.updateAssignment(assignment)
        }
    }
    
    fun deleteAssignment(assignment: Assignment) {
        viewModelScope.launch {
            repository.deleteAssignment(assignment)
        }
    }
}
