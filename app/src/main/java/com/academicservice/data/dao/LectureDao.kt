package com.academicservice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.academicservice.data.models.Lecture

@Dao
interface LectureDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(lecture: Lecture): Long
    
    @Update
    suspend fun updateLecture(lecture: Lecture)
    
    @Delete
    suspend fun deleteLecture(lecture: Lecture)
    
    @Query("SELECT * FROM lectures WHERE id = :id")
    fun getLectureById(id: Int): LiveData<Lecture>
    
    @Query("SELECT * FROM lectures WHERE courseId = :courseId ORDER BY lectureDate ASC, lectureTime ASC")
    fun getLecturesByCourse(courseId: Int): LiveData<List<Lecture>>
    
    @Query("""
        SELECT l.* FROM lectures l
        WHERE l.lectureDate >= :startDate AND l.lectureDate <= :endDate
        ORDER BY l.lectureDate ASC, l.lectureTime ASC
    """)
    fun getLecturesByDateRange(startDate: Long, endDate: Long): LiveData<List<Lecture>>
    
    @Query("""
        SELECT l.* FROM lectures l
        JOIN courses c ON l.courseId = c.id
        WHERE c.studentId = :studentId
        ORDER BY l.lectureDate ASC, l.lectureTime ASC
    """)
    fun getLecturesByStudent(studentId: Int): LiveData<List<Lecture>>
    
    @Query("""
        SELECT l.* FROM lectures l
        WHERE l.lectureDate >= :currentDate AND l.lectureDate <= :endOfDay
        ORDER BY l.lectureTime ASC
    """)
    fun getTodayLectures(currentDate: Long, endOfDay: Long): LiveData<List<Lecture>>
}
