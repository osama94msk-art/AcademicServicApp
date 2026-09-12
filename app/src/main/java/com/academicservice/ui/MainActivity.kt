package com.academicservice.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.academicservice.R
import com.academicservice.databinding.ActivityMainBinding
import com.academicservice.ui.adapter.StudentAdapter
import com.academicservice.ui.viewmodel.StudentViewModel
import com.academicservice.ui.viewmodel.AssignmentViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var assignmentViewModel: AssignmentViewModel
    private lateinit var studentAdapter: StudentAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        
        // Initialize ViewModels
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        assignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        
        // Setup RecyclerView
        setupRecyclerView()
        
        // Observe data
        observeData()
        
        // Setup click listeners
        setupClickListeners()
    }
    
    private fun setupRecyclerView() {
        studentAdapter = StudentAdapter { student ->
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("studentId", student.id)
            startActivity(intent)
        }
        binding.rvStudents.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }
    }
    
    private fun observeData() {
        studentViewModel.allStudents.observe(this) { students ->
            studentAdapter.submitList(students)
            if (students.isEmpty()) {
                binding.emptyStateView.visibility = View.VISIBLE
                binding.rvStudents.visibility = View.GONE
            } else {
                binding.emptyStateView.visibility = View.GONE
                binding.rvStudents.visibility = View.VISIBLE
            }
        }
        
        studentViewModel.totalRevenue.observe(this) { revenue ->
            binding.tvTotalRevenue.text = "الإجمالي: ${revenue ?: 0.0} ر.س"
        }
        
        studentViewModel.totalPaidAmount.observe(this) { paid ->
            binding.tvTotalPaid.text = "المدفوع: ${paid ?: 0.0} ر.س"
        }
        
        studentViewModel.totalRemainingAmount.observe(this) { remaining ->
            binding.tvTotalRemaining.text = "المتبقي: ${remaining ?: 0.0} ر.س"
        }
        
        assignmentViewModel.upcomingAssignments.observe(this) { assignments ->
            binding.tvUpcomingCount.text = "واجبات عاجلة: ${assignments.size}"
        }
        
        assignmentViewModel.overdueAssignments.observe(this) { assignments ->
            binding.tvOverdueCount.text = "واجبات متأخرة: ${assignments.size}"
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
        
        binding.btnCalendar.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        
        binding.btnFinancialReport.setOnClickListener {
            startActivity(Intent(this, FinancialReportActivity::class.java))
        }
        
        binding.btnAlerts.setOnClickListener {
            // Show alerts dialog
            showAlertsDialog()
        }
    }
    
    private fun showAlertsDialog() {
        // هنا يتم عرض الواجبات العاجلة والمتأخرة
        // سيتم تطويره لاحقاً
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Implement search functionality
                true
            }
            R.id.action_settings -> {
                // Open settings
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
