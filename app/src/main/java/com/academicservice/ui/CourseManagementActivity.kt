package com.academicservice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.academicservice.databinding.ActivityCourseManagementBinding
import com.academicservice.ui.adapter.AssignmentAdapter
import com.academicservice.ui.viewmodel.AssignmentViewModel
import com.google.android.material.tabs.TabLayoutMediator

class CourseManagementActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCourseManagementBinding
    private lateinit var assignmentViewModel: AssignmentViewModel
    private lateinit var assignmentAdapter: AssignmentAdapter
    
    private var courseId: Int = 0
    private var studentId: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        courseId = intent.getIntExtra("courseId", 0)
        studentId = intent.getIntExtra("studentId", 0)
        
        assignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        
        setupRecyclerView()
        setupTabs()
        observeData()
        setupClickListeners()
    }
    
    private fun setupRecyclerView() {
        assignmentAdapter = AssignmentAdapter()
        binding.rvAssignments.apply {
            layoutManager = LinearLayoutManager(this@CourseManagementActivity)
            adapter = assignmentAdapter
        }
    }
    
    private fun setupTabs() {
        // Setup tabs for different assignment statuses
        binding.tabLayout.apply {
            addTab(newTab().setText("الكل"))
            addTab(newTab().setText("قيد التنفيذ"))
            addTab(newTab().setText("مكتمل"))
            addTab(newTab().setText("متأخر"))
        }
        
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                loadAssignments(tab?.position ?: 0)
            }
            
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }
    
    private fun loadAssignments(tabPosition: Int) {
        when (tabPosition) {
            0 -> {
                assignmentViewModel.getAssignmentsByCourse(courseId).observe(this) { assignments ->
                    assignmentAdapter.submitList(assignments)
                }
            }
            1 -> {
                assignmentViewModel.getAssignmentsByCourse(courseId).observe(this) { assignments ->
                    val pending = assignments.filter { it.status.name == "PENDING" }
                    assignmentAdapter.submitList(pending)
                }
            }
            2 -> {
                assignmentViewModel.getAssignmentsByCourse(courseId).observe(this) { assignments ->
                    val completed = assignments.filter { it.status.name == "COMPLETED" }
                    assignmentAdapter.submitList(completed)
                }
            }
            3 -> {
                assignmentViewModel.getAssignmentsByCourse(courseId).observe(this) { assignments ->
                    val late = assignments.filter { it.status.name == "LATE" }
                    assignmentAdapter.submitList(late)
                }
            }
        }
    }
    
    private fun observeData() {
        loadAssignments(0)
    }
    
    private fun setupClickListeners() {
        binding.fabAddAssignment.setOnClickListener {
            // Open Add Assignment dialog
            showAddAssignmentDialog()
        }
    }
    
    private fun showAddAssignmentDialog() {
        // Implementation for adding assignment
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
