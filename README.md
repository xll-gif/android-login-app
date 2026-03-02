# Android 登录应用

## 项目概述
基于 Kotlin + Jetpack Compose 实现的企业级登录应用，支持多端联调。

## 技术栈
- **语言**: Kotlin 1.9+
- **UI 框架**: Jetpack Compose
- **最低 SDK**: API 24 (Android 7.0)
- **目标 SDK**: API 34 (Android 14)
- **架构模式**: MVVM
- **网络库**: Retrofit + OkHttp

## 功能特性
- 用户登录（用户名/密码）
- 表单验证
- 加载状态提示
- 错误处理
- Mock 数据联调支持

## 项目结构
```
android-login-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/company/loginapp/
│   │   │   │   ├── MainActivity.kt           # 应用入口
│   │   │   │   ├── LoginApp.kt               # Application 类
│   │   │   │   ├── ui/
│   │   │   │   │   ├── theme/               # 主题配置
│   │   │   │   │   ├── login/
│   │   │   │   │   │   ├── LoginScreen.kt           # 登录页面
│   │   │   │   │   │   ├── LoginViewModel.kt        # 登录视图模型
│   │   │   │   │   │   └── components/             # 登录组件
│   │   │   │   │   │       ├── LoginButton.kt      # 登录按钮
│   │   │   │   │   │       └── LoginTextField.kt   # 输入框
│   │   │   │   │   └── home/
│   │   │   │   │       └── HomeScreen.kt           # 首页
│   │   │   │   ├── data/
│   │   │   │   │   ├── api/                # API 定义
│   │   │   │   │   │   ├── ApiService.kt           # API 服务
│   │   │   │   │   │   └── models/
│   │   │   │   │   │       ├── LoginRequest.kt     # 登录请求
│   │   │   │   │   │       └── LoginResponse.kt    # 登录响应
│   │   │   │   │   └── mock/               # Mock 数据
│   │   │   │   └── di/                     # 依赖注入
│   │   │   ├── res/                        # 资源文件
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## 开发说明
- 使用 Android Studio Hedgehog+ 打开项目
- 命令行构建：`./gradlew assembleDebug`
- 运行测试：`./gradlew test`

## 组件映射
- 通用按钮 -> Compose `Button`
- 通用输入框 -> Compose `TextField`
- 通用图片 -> Compose `AsyncImage` (Coil)

## 生成说明
本项目由自动化工作流生成，基于以下输入：
- 需求文档：GitHub Issue
- 设计稿：MasterGo（通过 Magic MCP 集成）
- API 定义：Postman Collection
