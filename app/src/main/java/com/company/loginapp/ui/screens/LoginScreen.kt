package com.company.loginapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.loginapp.api.LoginErrorType
import com.company.loginapp.api.LoginErrorMessages
import com.company.loginapp.api.LoginRequest
import com.company.loginapp.api.MockLoginService
import com.company.loginapp.ui.components.Button
import com.company.loginapp.ui.components.InputField
import kotlinx.coroutines.delay

/**
 * 登录页面
 *
 * 功能：
 * 1. 邮箱/密码登录
 * 2. 实时表单验证
 * 3. 错误提示
 * 4. 加载状态
 * 5. 登录成功后跳转
 *
 * @param onLoginSuccess 登录成功回调
 */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    // 表单状态
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    
    // 验证状态
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var submitError by remember { mutableStateOf<String?>(null) }
    
    // 加载状态
    var isLoading by remember { mutableStateOf(false) }
    
    // 登录服务
    val loginService = remember { MockLoginService() }
    
    // 邮箱验证
    LaunchedEffect(email) {
        if (email.isEmpty()) {
            emailError = null
        } else if (!loginService.validateEmail(email)) {
            emailError = LoginErrorMessages[LoginErrorType.INVALID_EMAIL]
        } else {
            emailError = null
        }
    }
    
    // 密码验证
    LaunchedEffect(password) {
        if (password.isEmpty()) {
            passwordError = null
        } else {
            val result = loginService.validatePassword(password)
            passwordError = result.second
        }
    }
    
    // 判断表单是否有效
    val isFormValid = email.isNotEmpty() &&
            password.isNotEmpty() &&
            emailError == null &&
            passwordError == null
    
    // 判断登录按钮是否禁用
    val isButtonDisabled = !isFormValid || isLoading
    
    // 处理登录
    val handleLogin: () -> Unit = {
        // 清除之前的提交错误
        submitError = null
        
        // 表单验证
        if (!isFormValid) return
        
        // 开始加载
        isLoading = true
        
        // 调用登录 API
        // 在实际应用中，这里应该调用 ViewModel
        // 这里使用模拟延迟
        LaunchedEffect(Unit) {
            delay(1000) // 模拟网络延迟
            
            // 模拟登录成功
            isLoading = false
            onLoginSuccess()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Logo
        LogoView()
        
        // 标题
        Text(
            text = "登录",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        
        // 表单
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // 邮箱输入框
            InputField(
                title = "邮箱",
                placeholder = "请输入邮箱地址",
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email,
                isError = emailError != null,
                errorMessage = emailError
            )
            
            // 密码输入框
            InputField(
                title = "密码",
                placeholder = "请输入密码",
                value = password,
                onValueChange = { password = it },
                isPassword = !showPassword,
                isError = passwordError != null,
                errorMessage = passwordError
            )
            
            // 密码显示/隐藏切换
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { showPassword = !showPassword }) {
                    Text(
                        text = if (showPassword) "隐藏密码" else "显示密码",
                        color = Color(0xFF007AFF)
                    )
                }
            }
            
            // 登录按钮
            Button(
                title = if (isLoading) "登录中..." else "登录",
                enabled = !isButtonDisabled,
                modifier = Modifier.fillMaxWidth(),
                onClick = handleLogin
            )
            
            // 提交错误提示
            if (submitError != null) {
                Text(
                    text = submitError!!,
                    color = Color(0xFFFF3B30),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF5F5))
                        .padding(12.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // 辅助链接
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                title = "忘记密码？",
                variant = com.company.loginapp.ui.components.ButtonVariant.SECONDARY,
                modifier = Modifier.width(200.dp),
                onClick = {}
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "还没有账号？",
                    color = Color(0xFF666666),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
                Button(
                    title = "立即注册",
                    variant = com.company.loginapp.ui.components.ButtonVariant.SECONDARY,
                    modifier = Modifier.width(60.dp),
                    onClick = {}
                )
            }
        }
        
        // 测试账号提示
        TestAccountsView()
    }
}

/**
 * Logo 视图
 */
@Composable
fun LogoView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF007AFF),
                            Color(0xFF5AC8FA)
                        )
                    ),
                    shape = RoundedCornerShape(40.dp)
                )
        ) {
            Text(
                text = "L",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

/**
 * 测试账号视图
 */
@Composable
fun TestAccountsView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "测试账号：",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF666666)
        )
        Text(
            text = "邮箱: test@example.com / 密码: password123",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            color = Color(0xFF999999)
        )
        Text(
            text = "邮箱: admin@example.com / 密码: admin123",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            color = Color(0xFF999999)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}
