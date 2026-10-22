package com.example.event_validation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var tilFullName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var tilStudentId: TextInputLayout
    private lateinit var tilClass: TextInputLayout

    private lateinit var edtFullName: TextInputEditText
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtPhone: TextInputEditText
    private lateinit var edtStudentId: TextInputEditText
    private lateinit var edtClass: TextInputEditText

    private lateinit var btnRegister: Button
    private lateinit var btnGoToDetail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()

        // 1. Chuyển thẳng sang màn hình Chi tiết bằng Intent
        btnGoToDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // 2. Kiểm tra validation và chuyển dữ liệu sang DetailActivity
        btnRegister.setOnClickListener {
            if (validateForm()) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("FULL_NAME", edtFullName.text.toString().trim())
                    putExtra("EMAIL", edtEmail.text.toString().trim())
                    putExtra("PHONE", edtPhone.text.toString().trim())
                    putExtra("STUDENT_ID", edtStudentId.text.toString().trim())
                    putExtra("CLASS", edtClass.text.toString().trim())
                }
                startActivity(intent)
            }
        }
    }

    private fun initViews() {
        tilFullName = findViewById(R.id.tilFullName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPhone = findViewById(R.id.tilPhone)
        tilStudentId = findViewById(R.id.tilStudentId)
        tilClass = findViewById(R.id.tilClass)

        edtFullName = findViewById(R.id.edtFullName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        edtStudentId = findViewById(R.id.edtStudentId)
        edtClass = findViewById(R.id.edtClass)

        btnRegister = findViewById(R.id.btnRegister)
        btnGoToDetail = findViewById(R.id.btnGoToDetail)
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val fullName = edtFullName.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val studentId = edtStudentId.text.toString().trim()
        val className = edtClass.text.toString().trim()

        // Kiểm tra Họ tên
        if (fullName.isEmpty()) {
            tilFullName.error = "Vui lòng nhập họ và tên"
            isValid = false
        } else {
            tilFullName.error = null
        }

        // Kiểm tra Email
        if (email.isEmpty()) {
            tilEmail.error = "Vui lòng nhập email"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Email không hợp lệ!"
            isValid = false
        } else {
            tilEmail.error = null
        }

        // Kiểm tra Số điện thoại (10 chữ số, bắt đầu bằng 0)
        val phonePattern = "^0[0-9]{9}$".toRegex()
        if (phone.isEmpty()) {
            tilPhone.error = "Vui lòng nhập số điện thoại"
            isValid = false
        } else if (!phone.matches(phonePattern)) {
            tilPhone.error = "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0"
            isValid = false
        } else {
            tilPhone.error = null
        }

        // Kiểm tra Mã sinh viên
        if (studentId.isEmpty()) {
            tilStudentId.error = "Vui lòng nhập mã sinh viên"
            isValid = false
        } else {
            tilStudentId.error = null
        }

        // Kiểm tra Lớp
        if (className.isEmpty()) {
            tilClass.error = "Vui lòng nhập tên lớp"
            isValid = false
        } else {
            tilClass.error = null
        }

        return isValid
    }
}