package com.academicservice.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.academicservice.data.database.AppDatabase
import com.academicservice.data.models.Course
import com.academicservice.data.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = CourseRepository(database.courseDao())
    
    fun getCoursesByStudent(studentId: Int): LiveData<List<Course>> {
        return repository.getCoursesByStudent(studentId)
    }
    
    fun getCourseCountByStudent(studentId: Int): LiveData<Int> {
        return repository.getCourseCountByStudent(studentId)
    }
    
    fun getCourseById(id: Int): LiveData<Course> {
        return repository.getCourseById(id)
    }
    
    fun searchCourses(query: String): LiveData<List<Course>> {
        return repository.searchCourses(query)
    }
    
    fun insertCourse(course: Course) {
        viewModelScope.launch {
            repository.insertCourse(course)
        }
    }
    
    fun updateCourse(course: Course) {
        viewModelScope.launch {
            repository.updateCourse(course)
        }
    }
    
    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            repository.deleteCourse(course)
        }
    }
}
