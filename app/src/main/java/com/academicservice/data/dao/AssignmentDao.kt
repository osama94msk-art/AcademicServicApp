package com.academicservice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.academicservice.data.models.Assignment
import com.academicservice.data.models.AssignmentStatus

@Dao
interface AssignmentDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment): Long
    
    @Update
    suspend fun updateAssignment(assignment: Assignment)
    
    @Delete
    suspend fun deleteAssignment(assignment: Assignment)
    
    @Query("SELECT * FROM assignments WHERE id = :id")
    fun getAssignmentById(id: Int): LiveData<Assignment>
    
    @Query("SELECT * FROM assignments WHERE courseId = :courseId ORDER BY dueDate ASC")
    fun getAssignmentsByCourse(courseId: Int): LiveData<List<Assignment>>
    
    @Query("""
        SELECT a.* FROM assignments a
        JOIN courses c ON a.courseId = c.id
        WHERE c.studentId = :studentId
        ORDER BY a.dueDate ASC
    """)
    fun getAssignmentsByStudent(studentId: Int): LiveData<List<Assignment>>
    
    @Query("""
        SELECT a.* FROM assignments a
        WHERE (a.dueDate - :currentTime) <= 172800000 AND (a.dueDate - :currentTime) > 0
        AND a.status != 'COMPLETED'
        ORDER BY a.dueDate ASC
    """)
    fun getUpcomingAssignments(currentTime: Long): LiveData<List<Assignment>>
    
    @Query("""
        SELECT a.* FROM assignments a
        WHERE a.dueDate < :currentTime AND a.status != 'COMPLETED'
        ORDER BY a.dueDate DESC
    """)
    fun getOverdueAssignments(currentTime: Long): LiveData<List<Assignment>>
    
    @Query("SELECT * FROM assignments WHERE status = :status ORDER BY dueDate ASC")
    fun getAssignmentsByStatus(status: AssignmentStatus): LiveData<List<Assignment>>
    
    @Query("SELECT COUNT(*) FROM assignments WHERE status = 'PENDING'")
    fun getPendingAssignmentCount(): LiveData<Int>
    
    @Query("SELECT COUNT(*) FROM assignments WHERE status = 'COMPLETED'")
    fun getCompletedAssignmentCount(): LiveData<Int>
    
    @Query("""
        SELECT a.* FROM assignments a
        WHERE a.dueDate >= :startDate AND a.dueDate <= :endDate
        ORDER BY a.dueDate ASC
    """)
    fun getAssignmentsByDateRange(startDate: Long, endDate: Long): LiveData<List<Assignment>>
}
