package com.company.loginapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * 启动页面组件
 *
 * 功能：
 * 1. 展示品牌 Logo 和加载动画
 * 2. 自动跳转到登录页面（延迟 2 秒）
 *
 * @param onLoadingComplete 加载完成回调
 */
@Composable
fun SplashScreen(
    onLoadingComplete: () -> Unit = {}
) {
    var progress by remember { mutableFloatStateOf(0f) }
    var isLoaded by remember { mutableStateOf(false) }

    // 加载动画
    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(100)
            progress = (progress + 0.05f).coerceAtMost(1f)
        }
        isLoaded = true
        delay(500)
        onLoadingComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Logo
            LogoView()

            Spacer(modifier = Modifier.height(24.dp))

            // 品牌名称
            BrandNameView(isLoaded = isLoaded)

            Spacer(modifier = Modifier.height(24.dp))

            // 加载动画
            LoadingIndicatorView(progress = progress)

            Spacer(modifier = Modifier.weight(1f))

            // 版本信息
            VersionInfoView()
        }
    }
}

/**
 * Logo 视图
 */
@Composable
private fun LogoView() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF007AFF),
                        Color(0xFF5AC8FA)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "L",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/**
 * 品牌名称视图
 */
@Composable
private fun BrandNameView(isLoaded: Boolean) {
    Text(
        text = "LoginApp",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF333333),
        modifier = Modifier.alpha(if (isLoaded) 1f else 0f)
    )
}

/**
 * 加载指示器视图
 */
@Composable
private fun LoadingIndicatorView(progress: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 进度条
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(4.dp)
                .background(Color(0xFFE5E5E5), CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF007AFF),
                                Color(0xFF5AC8FA)
                            )
                        ),
                        CircleShape
                    )
            )
        }

        // 加载文字
        Text(
            text = "正在加载...",
            fontSize = 14.sp,
            color = Color(0xFF999999)
        )
    }
}

/**
 * 版本信息视图
 */
@Composable
private fun VersionInfoView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Text(
            text = "Version 1.0.0",
            fontSize = 12.sp,
            color = Color(0xFF999999)
        )

        Text(
            text = "© 2026 Multi-Platform App",
            fontSize = 10.sp,
            color = Color(0xFFCCCCCC)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
