package com.company.loginapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 通用登录按钮组件
 *
 * 基于设计稿样式：
 * - 背景色: #007AFF
 * - 文字颜色: #FFFFFF
 * - 圆角: 4.dp
 * - 高度: 44.dp
 *
 * 使用规则：
 * 1. 主要操作按钮使用 variant = ButtonVariant.PRIMARY（默认）
 * 2. 次要操作按钮使用 variant = ButtonVariant.SECONDARY
 * 3. 危险操作按钮使用 variant = ButtonVariant.DANGER
 * 4. 禁用状态设置 enabled = false
 * 5. 按钮宽度建议使用固定宽度（如 300.dp）或 Modifier.fillMaxWidth()
 * 6. 按钮高度固定为 44.dp
 *
 * @param title 按钮文字
 * @param variant 按钮样式变体（默认：PRIMARY）
 * @param enabled 是否可用（默认：true）
 * @param modifier 修饰符（默认：Modifier.height(44.dp)）
 * @param onClick 点击事件
 */
@Composable
fun Button(
    title: String,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    enabled: Boolean = true,
    modifier: Modifier = Modifier.height(44.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = getBackgroundColor(variant, enabled),
            contentColor = getContentColor(variant, enabled),
            disabledContainerColor = Color(0xFFE5E5E5),
            disabledContentColor = Color(0xFF999999)
        ),
        border = getBorderStroke(variant, enabled),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}

/**
 * 获取按钮背景色
 */
private fun getBackgroundColor(variant: ButtonVariant, enabled: Boolean): Color {
    return when (variant) {
        ButtonVariant.PRIMARY -> if (enabled) Color(0xFF007AFF) else Color(0xFFE5E5E5)
        ButtonVariant.SECONDARY -> if (enabled) Color(0xFFFFFFFF) else Color(0xFFE5E5E5)
        ButtonVariant.DANGER -> if (enabled) Color(0xFFFF3B30) else Color(0xFFE5E5E5)
    }
}

/**
 * 获取按钮文字颜色
 */
private fun getContentColor(variant: ButtonVariant, enabled: Boolean): Color {
    return when (variant) {
        ButtonVariant.PRIMARY -> if (enabled) Color(0xFFFFFFFF) else Color(0xFF999999)
        ButtonVariant.SECONDARY -> if (enabled) Color(0xFF007AFF) else Color(0xFF999999)
        ButtonVariant.DANGER -> if (enabled) Color(0xFFFFFFFF) else Color(0xFF999999)
    }
}

/**
 * 获取按钮边框
 */
private fun getBorderStroke(variant: ButtonVariant, enabled: Boolean): BorderStroke? {
    return when (variant) {
        ButtonVariant.PRIMARY -> null
        ButtonVariant.SECONDARY -> if (enabled) {
            BorderStroke(1.dp, Color(0xFF007AFF))
        } else {
            BorderStroke(1.dp, Color(0xFFDDDDDD))
        }
        ButtonVariant.DANGER -> null
    }
}

/**
 * 按钮样式变体枚举
 */
enum class ButtonVariant {
    /** 主要按钮（蓝色背景，白色文字） */
    PRIMARY,
    
    /** 次要按钮（白色背景，蓝色文字，蓝色边框） */
    SECONDARY,
    
    /** 危险按钮（红色背景，白色文字） */
    DANGER
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Column(
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            title = "登录",
            modifier = Modifier.width(300.dp),
            onClick = {}
        )
        
        Button(
            title = "注册",
            variant = ButtonVariant.SECONDARY,
            modifier = Modifier.width(300.dp),
            onClick = {}
        )
        
        Button(
            title = "退出登录",
            variant = ButtonVariant.DANGER,
            modifier = Modifier.width(300.dp),
            onClick = {}
        )
        
        Button(
            title = "禁用状态",
            enabled = false,
            modifier = Modifier.width(300.dp),
            onClick = {}
        )
    }
}
