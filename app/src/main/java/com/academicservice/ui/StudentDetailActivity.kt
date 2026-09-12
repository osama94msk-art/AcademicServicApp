package com.academicservice.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.academicservice.databinding.ActivityStudentDetailBinding
import com.academicservice.ui.adapter.CourseAdapter
import com.academicservice.ui.viewmodel.StudentViewModel
import com.academicservice.ui.viewmodel.AssignmentViewModel
import com.google.android.material.snackbar.Snackbar

class StudentDetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStudentDetailBinding
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var assignmentViewModel: AssignmentViewModel
    private lateinit var courseAdapter: CourseAdapter
    
    private var studentId: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        studentId = intent.getIntExtra("studentId", 0)
        
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        assignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        
        setupRecyclerView()
        observeData()
        setupClickListeners()
    }
    
    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter { course ->
            val intent = Intent(this, CourseManagementActivity::class.java)
            intent.putExtra("courseId", course.id)
            intent.putExtra("studentId", studentId)
            startActivity(intent)
        }
        binding.rvCourses.apply {
            layoutManager = LinearLayoutManager(this@StudentDetailActivity)
            adapter = courseAdapter
        }
    }
    
    private fun observeData() {
        studentViewModel.getStudentById(studentId).observe(this) { student ->
            binding.tvStudentName.text = student.name
            binding.tvUniversityId.text = "الرقم الجامعي: ${student.universityId}"
            binding.tvSpecialty.text = "التخصص: ${student.specialty}"
            binding.tvLevel.text = "المستوى: ${student.level}"
            binding.tvPhone.text = "الجوال: ${student.phoneNumber}"
            
            // Financial info
            binding.tvTotalAmount.text = "المبلغ الإجمالي: ${student.totalAmount} ر.س"
            binding.tvPaidAmount.text = "المدفوع: ${student.paidAmount} ر.س"
            binding.tvRemainingAmount.text = "المتبقي: ${student.remainingAmount} ر.س"
            
            // Payment progress
            binding.progressBar.progress = student.getPaymentPercentage().toInt()
            binding.tvPaymentPercentage.text = "${student.getPaymentPercentage().toInt()}%"
        }
        
        // ObserveCourses - will be implemented with CourseRepository
        // For now, we'll leave this as a placeholder
    }
    
    private fun setupClickListeners() {
        binding.fabAddCourse.setOnClickListener {
            // Open Add Course dialog or activity
            showAddCourseDialog()
        }
        
        binding.btnViewFinancial.setOnClickListener {
            val intent = Intent(this, FinancialReportActivity::class.java)
            intent.putExtra("studentId", studentId)
            startActivity(intent)
        }
        
        binding.btnAssignments.setOnClickListener {
            // Show student's assignments
            assignmentViewModel.getAssignmentsByStudent(studentId).observe(this) { assignments ->
                // Update UI with assignments
            }
        }
    }
    
    private fun showAddCourseDialog() {
        Snackbar.make(binding.root, "سيتم فتح نافذة إضافة المقرر", Snackbar.LENGTH_SHORT).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
