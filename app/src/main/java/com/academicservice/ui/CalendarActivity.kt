package com.academicservice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.academicservice.databinding.ActivityCalendarBinding
import com.academicservice.ui.adapter.AssignmentAdapter
import com.academicservice.ui.viewmodel.AssignmentViewModel
import java.util.*

class CalendarActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCalendarBinding
    private lateinit var assignmentViewModel: AssignmentViewModel
    private lateinit var assignmentAdapter: AssignmentAdapter
    
    private var selectedDate: Long = System.currentTimeMillis()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        assignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        
        setupRecyclerView()
        setupCalendarView()
        observeData()
    }
    
    private fun setupRecyclerView() {
        assignmentAdapter = AssignmentAdapter()
        binding.rvAssignments.apply {
            layoutManager = LinearLayoutManager(this@CalendarActivity)
            adapter = assignmentAdapter
        }
    }
    
    private fun setupCalendarView() {
        // Setup calendar using Kizitonwose Calendar Library
        // This will show monthly view with assignment indicators
        
        binding.btnPreviousMonth.setOnClickListener {
            // Previous month
        }
        
        binding.btnNextMonth.setOnClickListener {
            // Next month
        }
        
        binding.btnToday.setOnClickListener {
            selectedDate = System.currentTimeMillis()
            loadAssignmentsForDate()
        }
    }
    
    private fun observeData() {
        loadAssignmentsForDate()
    }
    
    private fun loadAssignmentsForDate() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate
        
        // Get start and end of day
        val startDate = calendar.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis
        
        val endDate = calendar.apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }.timeInMillis
        
        assignmentViewModel.getAssignmentsByDateRange(startDate, endDate).observe(this) { assignments ->
            assignmentAdapter.submitList(assignments)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
