package com.academicservice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.academicservice.databinding.ItemStudentBinding
import com.academicservice.data.models.Student

class StudentAdapter(
    private val onItemClick: (Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }
    
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class StudentViewHolder(
        private val binding: ItemStudentBinding,
        private val onItemClick: (Student) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(student: Student) {
            binding.apply {
                tvStudentName.text = student.name
                tvUniversityId.text = "ID: ${student.universityId}"
                tvSpecialty.text = student.specialty
                tvLevel.text = "المستوى: ${student.level}"
                tvPaymentStatus.text = "المدفوع: ${student.paidAmount} / ${student.totalAmount}"
                
                progressPayment.progress = student.getPaymentPercentage().toInt()
                tvPaymentPercentage.text = "${student.getPaymentPercentage().toInt()}%"
                
                root.setOnClickListener {
                    onItemClick(student)
                }
            }
        }
    }
    
    class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }
}
