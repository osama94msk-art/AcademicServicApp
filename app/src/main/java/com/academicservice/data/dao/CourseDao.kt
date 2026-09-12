package com.academicservice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.academicservice.data.models.Course

@Dao
interface CourseDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course): Long
    
    @Update
    suspend fun updateCourse(course: Course)
    
    @Delete
    suspend fun deleteCourse(course: Course)
    
    @Query("SELECT * FROM courses WHERE id = :id")
    fun getCourseById(id: Int): LiveData<Course>
    
    @Query("SELECT * FROM courses WHERE studentId = :studentId ORDER BY courseName ASC")
    fun getCoursesByStudent(studentId: Int): LiveData<List<Course>>
    
    @Query("SELECT COUNT(*) FROM courses WHERE studentId = :studentId")
    fun getCourseCountByStudent(studentId: Int): LiveData<Int>
    
    @Query("SELECT * FROM courses WHERE courseName LIKE '%' || :searchQuery || '%' OR courseCode LIKE '%' || :searchQuery || '%'")
    fun searchCourses(searchQuery: String): LiveData<List<Course>>
}
