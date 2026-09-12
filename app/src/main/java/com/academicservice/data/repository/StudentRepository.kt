package com.academicservice.data.repository

import androidx.lifecycle.LiveData
import com.academicservice.data.dao.*
import com.academicservice.data.models.Student

class StudentRepository(
    private val studentDao: StudentDao,
    private val courseDao: CourseDao,
    private val assignmentDao: AssignmentDao,
    private val financialRecordDao: FinancialRecordDao,
    private val lectureDao: LectureDao
) {
    
    // Student operations
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()
    val studentCount: LiveData<Int> = studentDao.getStudentCount()
    val totalRevenue: LiveData<Double> = studentDao.getTotalRevenue()
    val totalPaidAmount: LiveData<Double> = studentDao.getTotalPaidAmount()
    val totalRemainingAmount: LiveData<Double> = studentDao.getTotalRemainingAmount()
    
    suspend fun insertStudent(student: Student): Long {
        return studentDao.insertStudent(student)
    }
    
    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }
    
    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }
    
    fun getStudentById(id: Int): LiveData<Student> {
        return studentDao.getStudentById(id)
    }
    
    fun searchStudents(query: String): LiveData<List<Student>> {
        return studentDao.searchStudents(query)
    }
    
    fun getStudentsBySpecialty(specialty: String): LiveData<List<Student>> {
        return studentDao.getStudentsBySpecialty(specialty)
    }
    
    fun getStudentsOrderByDate(): LiveData<List<Student>> {
        return studentDao.getStudentsOrderByDate()
    }
}
