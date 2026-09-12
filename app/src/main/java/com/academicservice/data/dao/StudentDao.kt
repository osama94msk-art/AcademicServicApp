package com.academicservice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.academicservice.data.models.Student

@Dao
interface StudentDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student): Long
    
    @Update
    suspend fun updateStudent(student: Student)
    
    @Delete
    suspend fun deleteStudent(student: Student)
    
    @Query("SELECT * FROM students WHERE id = :id")
    fun getStudentById(id: Int): LiveData<Student>
    
    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): LiveData<List<Student>>
    
    @Query("SELECT * FROM students WHERE name LIKE '%' || :searchQuery || '%' OR universityId LIKE '%' || :searchQuery || '%'")
    fun searchStudents(searchQuery: String): LiveData<List<Student>>
    
    @Query("SELECT COUNT(*) FROM students")
    fun getStudentCount(): LiveData<Int>
    
    @Query("SELECT SUM(totalAmount) FROM students")
    fun getTotalRevenue(): LiveData<Double>
    
    @Query("SELECT SUM(paidAmount) FROM students")
    fun getTotalPaidAmount(): LiveData<Double>
    
    @Query("SELECT SUM(remainingAmount) FROM students")
    fun getTotalRemainingAmount(): LiveData<Double>
    
    @Query("SELECT * FROM students ORDER BY createdDate DESC")
    fun getStudentsOrderByDate(): LiveData<List<Student>>
    
    @Query("SELECT * FROM students WHERE specialty = :specialty")
    fun getStudentsBySpecialty(specialty: String): LiveData<List<Student>>
}
