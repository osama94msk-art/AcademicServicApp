package com.academicservice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.academicservice.databinding.ItemCourseBinding
import com.academicservice.data.models.Course

class CourseAdapter(
    private val onItemClick: (Course) -> Unit
) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }
    
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val onItemClick: (Course) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(course: Course) {
            binding.apply {
                tvCourseName.text = course.courseName
                tvCourseCode.text = "الرمز: ${course.courseCode}"
                tvInstructor.text = "المحاضر: ${course.instructor}"
                tvSchedule.text = "الموعد: ${course.scheduleTime}"
                tvRoom.text = "القاعة: ${course.room}"
                
                root.setOnClickListener {
                    onItemClick(course)
                }
            }
        }
    }
    
    class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}
