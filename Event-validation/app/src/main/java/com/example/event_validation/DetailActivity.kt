package com.example.event_validation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvFullName = findViewById<TextView>(R.id.tvFullName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvStudentId = findViewById<TextView>(R.id.tvStudentId)
        val tvClass = findViewById<TextView>(R.id.tvClass)
        val btnBackToRegister = findViewById<Button>(R.id.btnBackToRegister)

        // Lấy thông tin từ Intent truyền tới
        val fullName = intent.getStringExtra("FULL_NAME") ?: "Chưa có dữ liệu"
        val email = intent.getStringExtra("EMAIL") ?: "Chưa có dữ liệu"
        val phone = intent.getStringExtra("PHONE") ?: "Chưa có dữ liệu"
        val studentId = intent.getStringExtra("STUDENT_ID") ?: "Chưa có dữ liệu"
        val className = intent.getStringExtra("CLASS") ?: "Chưa có dữ liệu"

        // Hiển thị dữ liệu lên màn hình
        tvFullName.text = "Họ và tên: $fullName"
        tvEmail.text = "Email: $email"
        tvPhone.text = "Số điện thoại: $phone"
        tvStudentId.text = "Mã sinh viên: $studentId"
        tvClass.text = "Lớp: $className"

        // Chuyển ngược lại màn hình Đăng ký bằng Intent
        btnBackToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}