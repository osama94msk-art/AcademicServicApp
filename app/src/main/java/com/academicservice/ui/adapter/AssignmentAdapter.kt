package com.academicservice.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.academicservice.R
import com.academicservice.databinding.ItemAssignmentBinding
import com.academicservice.data.models.Assignment
import com.academicservice.data.models.getPriorityLevel
import com.academicservice.data.models.PriorityLevel
import java.text.SimpleDateFormat
import java.util.*

class AssignmentAdapter(
    private val onItemClick: ((Assignment) -> Unit)? = null
) : ListAdapter<Assignment, AssignmentAdapter.AssignmentViewHolder>(AssignmentDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        return AssignmentViewHolder(
            ItemAssignmentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }
    
    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class AssignmentViewHolder(
        private val binding: ItemAssignmentBinding,
        private val onItemClick: ((Assignment) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(assignment: Assignment) {
            binding.apply {
                tvAssignmentName.text = assignment.assignmentName
                tvDescription.text = assignment.description
                
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
                tvDueDate.text = "آخر موعد: ${dateFormat.format(Date(assignment.dueDate))}"
                
                // Set status
                tvStatus.text = when (assignment.status.name) {
                    "PENDING" -> "قيد التنفيذ"
                    "COMPLETED" -> "مكتمل"
                    "LATE" -> "متأخر"
                    "SUBMITTED" -> "تم التسليم"
                    else -> assignment.status.name
                }
                
                // Set priority color
                val priorityLevel = assignment.getPriorityLevel()
                val priorityColor = when (priorityLevel) {
                    PriorityLevel.CRITICAL -> Color.RED
                    PriorityLevel.HIGH -> Color.parseColor("#FF9800")
                    PriorityLevel.MEDIUM -> Color.parseColor("#FFC107")
                    PriorityLevel.LOW -> Color.GREEN
                }
                
                priorityIndicator.setBackgroundColor(priorityColor)
                
                root.setOnClickListener {
                    onItemClick?.invoke(assignment)
                }
            }
        }
    }
    
    class AssignmentDiffCallback : DiffUtil.ItemCallback<Assignment>() {
        override fun areItemsTheSame(oldItem: Assignment, newItem: Assignment): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Assignment, newItem: Assignment): Boolean {
            return oldItem == newItem
        }
    }
}
