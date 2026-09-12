package com.academicservice.data.repository

import androidx.lifecycle.LiveData
import com.academicservice.data.dao.CourseDao
import com.academicservice.data.models.Course

class CourseRepository(private val courseDao: CourseDao) {
    
    fun getCourseById(id: Int): LiveData<Course> {
        return courseDao.getCourseById(id)
    }
    
    fun getCoursesByStudent(studentId: Int): LiveData<List<Course>> {
        return courseDao.getCoursesByStudent(studentId)
    }
    
    fun getCourseCountByStudent(studentId: Int): LiveData<Int> {
        return courseDao.getCourseCountByStudent(studentId)
    }
    
    fun searchCourses(query: String): LiveData<List<Course>> {
        return courseDao.searchCourses(query)
    }
    
    suspend fun insertCourse(course: Course): Long {
        return courseDao.insertCourse(course)
    }
    
    suspend fun updateCourse(course: Course) {
        courseDao.updateCourse(course)
    }
    
    suspend fun deleteCourse(course: Course) {
        courseDao.deleteCourse(course)
    }
}
