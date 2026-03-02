package com.company.loginapp.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// ===== 数据模型 =====

/**
 * 登录请求参数
 */
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

/**
 * 登录响应数据
 */
@Serializable
data class LoginResponse(
    val token: String,
    val refreshToken: String,
    val userId: String,
    val email: String,
    val nickname: String,
    val avatar: String,
    val expiresIn: Int
)

/**
 * API 响应格式
 */
@Serializable
data class APIResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)

/**
 * 登录错误类型
 */
enum class LoginErrorType {
    INVALID_EMAIL,
    PASSWORD_TOO_SHORT,
    PASSWORD_TOO_LONG,
    INVALID_CREDENTIALS,
    ACCOUNT_LOCKED,
    NETWORK_ERROR,
    SERVER_ERROR
}

/**
 * 登录错误信息
 */
val LoginErrorMessages = mapOf(
    LoginErrorType.INVALID_EMAIL to "请输入有效的邮箱地址",
    LoginErrorType.PASSWORD_TOO_SHORT to "密码至少需要 6 个字符",
    LoginErrorType.PASSWORD_TOO_LONG to "密码不能超过 20 个字符",
    LoginErrorType.INVALID_CREDENTIALS to "邮箱或密码错误，请重试",
    LoginErrorType.ACCOUNT_LOCKED to "账号已被锁定，请联系客服",
    LoginErrorType.NETWORK_ERROR to "网络连接失败，请检查网络设置",
    LoginErrorType.SERVER_ERROR to "服务器繁忙，请稍后再试"
)

// ===== Mock 登录服务 =====

/**
 * Mock 登录服务
 */
class MockLoginService {
    
    companion object {
        /** Mock 延迟时间（毫秒） */
        private const val MOCK_DELAY = 1000L
        
        /** Mock 用户数据库 */
        private val mockUsers = listOf(
            MockUser(
                email = "test@example.com",
                password = "password123",
                userId = "MOCK_USER_001",
                nickname = "测试用户",
                avatar = "https://via.placeholder.com/100"
            ),
            MockUser(
                email = "admin@example.com",
                password = "admin123",
                userId = "MOCK_USER_002",
                nickname = "管理员",
                avatar = "https://via.placeholder.com/100"
            )
        )
    }
    
    /**
     * 用户登录
     * @param request 登录请求参数
     * @return 登录响应
     */
    suspend fun login(request: LoginRequest): Result<APIResponse<LoginResponse>> {
        return try {
            // 模拟网络延迟
            kotlinx.coroutines.delay(MOCK_DELAY)
            
            // 查找用户
            val user = mockUsers.find {
                it.email == request.email && it.password == request.password
            }
            
            if (user != null) {
                // 登录成功
                val response = LoginResponse(
                    token = "mock_token_${System.currentTimeMillis()}",
                    refreshToken = "mock_refresh_token_${System.currentTimeMillis()}",
                    userId = user.userId,
                    email = user.email,
                    nickname = user.nickname,
                    avatar = user.avatar,
                    expiresIn = 7200
                )
                
                Result.success(APIResponse(code = 200, message = "登录成功", data = response))
            } else {
                // 登录失败
                Result.success(APIResponse(code = 401, message = "邮箱或密码错误", data = null))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * 验证邮箱格式
     * @param email 邮箱地址
     * @return 是否有效
     */
    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$")
        return emailRegex.matches(email)
    }
    
    /**
     * 验证密码长度
     * @param password 密码
     * @return 验证结果（Pair<valid, error>）
     */
    fun validatePassword(password: String): Pair<Boolean, String?> {
        return when {
            password.length < 6 -> Pair(false, LoginErrorMessages[LoginErrorType.PASSWORD_TOO_SHORT])
            password.length > 20 -> Pair(false, LoginErrorMessages[LoginErrorType.PASSWORD_TOO_LONG])
            else -> Pair(true, null)
        }
    }
}

// ===== Mock 用户数据 =====

/**
 * Mock 用户数据
 */
private data class MockUser(
    val email: String,
    val password: String,
    val userId: String,
    val nickname: String,
    val avatar: String
)
