package com.academicservice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.academicservice.databinding.ActivityFinancialReportBinding
import com.academicservice.ui.adapter.FinancialRecordAdapter
import com.academicservice.ui.viewmodel.FinancialViewModel
import com.academicservice.ui.viewmodel.StudentViewModel
import java.text.SimpleDateFormat
import java.util.*

class FinancialReportActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFinancialReportBinding
    private lateinit var financialViewModel: FinancialViewModel
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var recordAdapter: FinancialRecordAdapter
    
    private var studentId: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        studentId = intent.getIntExtra("studentId", 0)
        
        financialViewModel = ViewModelProvider(this).get(FinancialViewModel::class.java)
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        
        setupRecyclerView()
        observeData()
        setupDateFilter()
    }
    
    private fun setupRecyclerView() {
        recordAdapter = FinancialRecordAdapter()
        binding.rvFinancialRecords.apply {
            layoutManager = LinearLayoutManager(this@FinancialReportActivity)
            adapter = recordAdapter
        }
    }
    
    private fun observeData() {
        if (studentId > 0) {
            // Show specific student's financial records
            financialViewModel.getRecordsByStudent(studentId).observe(this) { records ->
                recordAdapter.submitList(records)
                updateSummary()
            }
            
            studentViewModel.getStudentById(studentId).observe(this) { student ->
                binding.tvStudentName.text = student.name
                binding.tvTotalAmount.text = "المبلغ الإجمالي: ${student.totalAmount} ر.س"
                binding.tvPaidAmount.text = "المدفوع: ${student.paidAmount} ر.س"
                binding.tvRemainingAmount.text = "المتبقي: ${student.remainingAmount} ر.س"
                binding.progressBar.progress = student.getPaymentPercentage().toInt()
            }
        } else {
            // Show all financial records
            financialViewModel.totalPayments.observe(this) { total ->
                binding.tvTotalAmount.text = "إجمالي الدفعات: ${total ?: 0.0} ر.س"
            }
        }
    }
    
    private fun setupDateFilter() {
        binding.btnFilterByDate.setOnClickListener {
            // Implement date filter
            val startDate = System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000) // 30 days ago
            val endDate = System.currentTimeMillis()
            
            financialViewModel.getRecordsByDateRange(startDate, endDate).observe(this) { records ->
                recordAdapter.submitList(records)
            }
        }
        
        binding.btnExportReport.setOnClickListener {
            exportReport()
        }
    }
    
    private fun updateSummary() {
        // This will be updated based on the data
    }
    
    private fun exportReport() {
        // Generate and export financial report as PDF or CSV
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
