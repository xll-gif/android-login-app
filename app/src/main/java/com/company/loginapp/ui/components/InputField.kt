package com.company.loginapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 通用输入框组件
 *
 * 基于设计稿样式：
 * - 边框: #DDDDDD
 * - 边框宽度: 1.dp
 * - 圆角: 4.dp
 * - 高度: 40.dp
 * - 内边距: 12.dp
 *
 * 使用规则：
 * 1. 默认使用单行文本输入
 * 2. 密码输入设置 isPassword = true
 * 3. 邮箱输入设置 keyboardType = KeyboardType.Email
 * 4. 输入框宽度建议使用固定宽度（如 300.dp）或 Modifier.fillMaxWidth()
 * 5. 输入框高度固定为 40.dp
 * 6. 错误状态设置 isError = true
 *
 * @param title 输入框标题
 * @param placeholder 占位符（必填）
 * @param text 输入值绑定（必填）
 * @param keyboardType 键盘类型（默认：KeyboardType.Text）
 * @param isPassword 是否密码输入（默认：false）
 * @param enabled 是否启用（默认：true）
 * @param isError 是否有错误（默认：false）
 * @param errorMessage 错误提示
 * @param modifier 修饰符（默认：Modifier.height(40.dp)）
 */
@Composable
fun InputField(
    title: String? = null,
    placeholder: String,
    text: androidx.compose.runtime.State<String>,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier.height(40.dp),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        // 标题
        if (title != null) {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color(0xFF333333),
                fontWeight = if (isError) androidx.compose.ui.text.font.FontWeight.SemiBold 
                           else androidx.compose.ui.text.font.FontWeight.Normal
            )
        }
        
        // 输入框
        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF999999)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (isPassword) PasswordVisualTransformation() 
                                  else VisualTransformation.None,
            enabled = enabled,
            isError = isError,
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color(0xFF333333)
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedBorderColor = if (isError) Color(0xFFFF3B30) else Color(0xFFDDDDDD),
                unfocusedBorderColor = if (isError) Color(0xFFFF3B30) else Color(0xFFDDDDDD),
                disabledBorderColor = Color(0xFFDDDDDD),
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                disabledTextColor = Color(0xFF999999),
            ),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
        )
        
        // 错误提示
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color(0xFFFF3B30),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        val emailState = androidx.compose.runtime.mutableStateOf("")
        val passwordState = androidx.compose.runtime.mutableStateOf("")
        val phoneState = androidx.compose.runtime.mutableStateOf("")
        val errorState = androidx.compose.runtime.mutableStateOf("invalid")
        val disabledState = androidx.compose.runtime.mutableStateOf("")
        
        InputField(
            title = "邮箱",
            placeholder = "请输入邮箱",
            text = emailState,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.width(300.dp)
        )
        
        InputField(
            title = "密码",
            placeholder = "请输入密码",
            text = passwordState,
            isPassword = true,
            modifier = Modifier.width(300.dp)
        )
        
        InputField(
            title = "手机号",
            placeholder = "请输入手机号",
            text = phoneState,
            keyboardType = KeyboardType.Phone,
            modifier = Modifier.width(300.dp)
        )
        
        InputField(
            title = "邮箱",
            placeholder = "请输入邮箱",
            text = errorState,
            isError = true,
            errorMessage = "邮箱格式不正确",
            modifier = Modifier.width(300.dp)
        )
        
        InputField(
            placeholder = "禁用状态",
            text = disabledState,
            enabled = false,
            modifier = Modifier.width(300.dp)
        )
    }
}
