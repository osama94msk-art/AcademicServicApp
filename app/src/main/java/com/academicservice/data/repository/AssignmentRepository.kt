package com.academicservice.data.repository

import androidx.lifecycle.LiveData
import com.academicservice.data.dao.AssignmentDao
import com.academicservice.data.models.Assignment
import com.academicservice.data.models.AssignmentStatus

class AssignmentRepository(private val assignmentDao: AssignmentDao) {
    
    fun getUpcomingAssignments(): LiveData<List<Assignment>> {
        return assignmentDao.getUpcomingAssignments(System.currentTimeMillis())
    }
    
    fun getOverdueAssignments(): LiveData<List<Assignment>> {
        return assignmentDao.getOverdueAssignments(System.currentTimeMillis())
    }
    
    fun getAssignmentsByStudent(studentId: Int): LiveData<List<Assignment>> {
        return assignmentDao.getAssignmentsByStudent(studentId)
    }
    
    fun getAssignmentsByCourse(courseId: Int): LiveData<List<Assignment>> {
        return assignmentDao.getAssignmentsByCourse(courseId)
    }
    
    fun getAssignmentById(id: Int): LiveData<Assignment> {
        return assignmentDao.getAssignmentById(id)
    }
    
    fun getAssignmentsByStatus(status: AssignmentStatus): LiveData<List<Assignment>> {
        return assignmentDao.getAssignmentsByStatus(status)
    }
    
    fun getPendingAssignmentCount(): LiveData<Int> {
        return assignmentDao.getPendingAssignmentCount()
    }
    
    fun getCompletedAssignmentCount(): LiveData<Int> {
        return assignmentDao.getCompletedAssignmentCount()
    }
    
    fun getAssignmentsByDateRange(startDate: Long, endDate: Long): LiveData<List<Assignment>> {
        return assignmentDao.getAssignmentsByDateRange(startDate, endDate)
    }
    
    suspend fun insertAssignment(assignment: Assignment): Long {
        return assignmentDao.insertAssignment(assignment)
    }
    
    suspend fun updateAssignment(assignment: Assignment) {
        assignmentDao.updateAssignment(assignment)
    }
    
    suspend fun deleteAssignment(assignment: Assignment) {
        assignmentDao.deleteAssignment(assignment)
    }
}
