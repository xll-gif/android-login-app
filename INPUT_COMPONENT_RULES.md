# InputField 组件使用规则

## 组件说明

通用输入框组件，基于设计稿样式实现，使用 Jetpack Compose 构建。

## 设计稿样式规范

- **边框颜色**: `#DDDDDD`（浅灰色）
- **边框宽度**: `1.dp`
- **圆角**: `4.dp`
- **宽度**: `300.dp`（可根据容器调整）
- **高度**: `40.dp`（固定）
- **文字大小**: `16.sp`（MaterialTheme.typography.bodyMedium）
- **文字颜色**: `#333333`
- **占位符颜色**: `#999999`
- **内边距**: `12.dp`

## API 文档

### 函数签名

```kotlin
@Composable
fun InputField(
    title: String? = null,
    placeholder: String,
    text: State<String>,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier.height(40.dp),
)
```

### 参数说明

| 参数 | 类型 | 默认值 | 说明 |
|-----|------|--------|------|
| `title` | `String?` | `null` | 输入框标题 |
| `placeholder` | `String` | - | 占位符（必填） |
| `text` | `State<String>` | - | 输入值绑定（必填） |
| `keyboardType` | `KeyboardType` | `KeyboardType.Text` | 键盘类型 |
| `isPassword` | `Boolean` | `false` | 是否密码输入 |
| `enabled` | `Boolean` | `true` | 是否启用 |
| `isError` | `Boolean` | `false` | 是否有错误 |
| `errorMessage` | `String?` | `null` | 错误提示 |
| `modifier` | `Modifier` | `Modifier.height(40.dp)` | 修饰符，默认高度为 40.dp |

### KeyboardType 类型

| 值 | 说明 |
|---|------|
| `KeyboardType.Text` | 默认键盘 |
| `KeyboardType.Email` | 邮箱地址 |
| `KeyboardType.Phone` | 电话号码 |
| `KeyboardType.Number` | 数字键盘 |
| `KeyboardType.Decimal` | 小数键盘 |
| `KeyboardType.Uri` | 网址 |

## 使用规则

### 1. 基础输入框

```kotlin
val email = remember { mutableStateOf("") }

InputField(
    placeholder = "请输入邮箱",
    text = email,
    keyboardType = KeyboardType.Email,
    modifier = Modifier.width(300.dp)
)
```

### 2. 带标题的输入框

```kotlin
val password = remember { mutableStateOf("") }

InputField(
    title = "密码",
    placeholder = "请输入密码",
    text = password,
    isPassword = true,
    modifier = Modifier.width(300.dp)
)
```

### 3. 错误状态

```kotlin
val email = remember { mutableStateOf("") }
val isError = remember { mutableStateOf(false) }

InputField(
    title = "邮箱",
    placeholder = "请输入邮箱",
    text = email,
    isError = isError.value,
    errorMessage = "邮箱格式不正确",
    modifier = Modifier.width(300.dp)
)
```

### 4. 禁用状态

```kotlin
val disabledText = remember { mutableStateOf("已禁用") }

InputField(
    placeholder = "禁用状态",
    text = disabledText,
    enabled = false,
    modifier = Modifier.width(300.dp)
)
```

### 5. 手机号输入

```kotlin
val phone = remember { mutableStateOf("") }

InputField(
    title = "手机号",
    placeholder = "请输入手机号",
    text = phone,
    keyboardType = KeyboardType.Phone,
    modifier = Modifier.width(300.dp)
)
```

### 6. 数字输入

```kotlin
val amount = remember { mutableStateOf("") }

InputField(
    title = "金额",
    placeholder = "请输入金额",
    text = amount,
    keyboardType = KeyboardType.Decimal,
    modifier = Modifier.width(300.dp)
)
```

### 7. 使用 remember 管理状态

```kotlin
@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        InputField(
            title = "邮箱",
            placeholder = "请输入邮箱",
            text = email,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.width(300.dp)
        )
        
        InputField(
            title = "密码",
            placeholder = "请输入密码",
            text = password,
            isPassword = true,
            modifier = Modifier.width(300.dp)
        )
    }
}
```

## 布局建议

### 垂直表单布局

```kotlin
Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = Modifier.padding(16.dp)
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    
    InputField(
        title = "邮箱",
        placeholder = "请输入邮箱",
        text = email,
        keyboardType = KeyboardType.Email,
        modifier = Modifier.width(300.dp)
    )
    
    InputField(
        title = "密码",
        placeholder = "请输入密码",
        text = password,
        isPassword = true,
        modifier = Modifier.width(300.dp)
    )
}
```

### 错误验证流程

```kotlin
fun validateEmail(email: String): Boolean {
    val emailRegex = Regex("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")
    return emailRegex.matches(email)
}

fun handleSubmit() {
    if (!validateEmail(email.value)) {
        isError = true
        errorMessage = "邮箱格式不正确"
    } else {
        // 提交逻辑
    }
}
```

## 禁止事项

1. ❌ 不要使用非设计稿规范的颜色
2. ❌ 不要修改输入框高度（除非有特殊需求）
3. ❌ 不要在占位符中使用特殊符号
4. ❌ 不要嵌套输入框
5. ❌ 不要在 `modifier` 中重复设置 `height`（组件已设置默认值）
6. ❌ 不要同时使用错误状态和禁用状态（除非业务需要）

## Material Design 兼容性

- 使用 `MaterialTheme.typography` 作为字体大小基准
- 符合 Material Design OutlinedTextField 规范
- 支持 Material 3 设计语言

## 版本历史

- v1.0.0 (2026-02-27): 初始版本，基于设计稿创建
