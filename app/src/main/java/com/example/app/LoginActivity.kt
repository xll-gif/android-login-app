package com.example.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show()
            return
        }

        // 模拟登录
        progressBar.visibility = ProgressBar.VISIBLE
        loginButton.isEnabled = false
        loginButton.alpha = 0.5f

        Thread {
            Thread.sleep(1500)
            runOnUiThread {
                progressBar.visibility = ProgressBar.GONE
                loginButton.isEnabled = true
                loginButton.alpha = 1.0f

                if (username == "admin" && password == "123456") {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                    // 跳转到首页
                    // startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}
