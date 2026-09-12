package com.academicservice.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.academicservice.databinding.ActivityAddStudentBinding
import com.academicservice.data.models.Student
import com.academicservice.ui.viewmodel.StudentViewModel
import com.google.android.material.snackbar.Snackbar

class AddStudentActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var studentViewModel: StudentViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.btnShowPassword.setOnClickListener {
            togglePasswordVisibility()
        }
        
        binding.btnSaveStudent.setOnClickListener {
            saveStudent()
        }
    }
    
    private fun togglePasswordVisibility() {
        val isPasswordVisible = binding.etPassword.inputType == android.text.InputType.TYPE_CLASS_TEXT
        
        if (isPasswordVisible) {
            binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.btnShowPassword.text = "عرض"
        } else {
            binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT
            binding.btnShowPassword.text = "إخفاء"
        }
        binding.etPassword.setSelection(binding.etPassword.text.length)
    }
    
    private fun saveStudent() {
        val name = binding.etName.text.toString().trim()
        val universityId = binding.etUniversityId.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val specialty = binding.etSpecialty.text.toString().trim()
        val level = binding.etLevel.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()
        val totalAmount = binding.etTotalAmount.text.toString().toDoubleOrNull() ?: 0.0
        val paidAmount = binding.etPaidAmount.text.toString().toDoubleOrNull() ?: 0.0
        
        // Validation
        if (name.isEmpty() || universityId.isEmpty() || password.isEmpty() || 
            specialty.isEmpty() || level.isEmpty() || phoneNumber.isEmpty()) {
            Snackbar.make(binding.root, "يرجى ملء جميع الحقول المطلوبة", Snackbar.LENGTH_SHORT).show()
            return
        }
        
        val student = Student(
            name = name,
            universityId = universityId,
            password = password,
            specialty = specialty,
            level = level,
            phoneNumber = phoneNumber,
            totalAmount = totalAmount,
            paidAmount = paidAmount,
            remainingAmount = totalAmount - paidAmount
        )
        
        studentViewModel.insertStudent(student)
        
        Snackbar.make(binding.root, "تم إضافة الطالب بنجاح", Snackbar.LENGTH_SHORT).show()
        
        // Close activity after 1 second
        binding.root.postDelayed({
            finish()
        }, 1000)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
