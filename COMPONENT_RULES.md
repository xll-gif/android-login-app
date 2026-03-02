# Button 组件使用规则

## 组件说明

通用登录按钮组件，基于设计稿样式实现，使用 Jetpack Compose 构建。

## 设计稿样式规范

- **背景色**: `#007AFF`（蓝色）
- **文字颜色**: `#FFFFFF`（白色）
- **圆角**: `4.dp`
- **宽度**: `300.dp`（可根据容器调整）
- **高度**: `44.dp`（固定）
- **文字大小**: `16.sp`（MaterialTheme.typography.bodyMedium）
- **文字粗细**: `FontWeight.Medium (500)`

## API 文档

### 函数签名

```kotlin
@Composable
fun Button(
    title: String,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    enabled: Boolean = true,
    modifier: Modifier = Modifier.height(44.dp),
    onClick: () -> Unit
)
```

### 参数说明

| 参数 | 类型 | 默认值 | 说明 |
|-----|------|--------|------|
| `title` | `String` | - | 按钮文字（必填） |
| `variant` | `ButtonVariant` | `PRIMARY` | 按钮样式变体 |
| `enabled` | `Boolean` | `true` | 是否可用 |
| `modifier` | `Modifier` | `Modifier.height(44.dp)` | 修饰符，默认高度为 44.dp |
| `onClick` | `() -> Unit` | - | 点击事件回调（必填） |

### ButtonVariant 枚举

| 值 | 说明 | 背景色 | 文字颜色 | 边框 |
|---|---|---|---|---|
| `PRIMARY` | 主要按钮（默认） | `#007AFF` | `#FFFFFF` | 无 |
| `SECONDARY` | 次要按钮 | `#FFFFFF` | `#007AFF` | `#007AFF` (1.dp) |
| `DANGER` | 危险按钮 | `#FF3B30` | `#FFFFFF` | 无 |

## 使用规则

### 1. 主要操作按钮

用于主要操作，如登录、提交、确认等。

```kotlin
Button(
    title = "登录",
    modifier = Modifier.width(300.dp),
    onClick = {
        // 处理登录逻辑
    }
)
```

### 2. 次要操作按钮

用于次要操作，如取消、返回等。

```kotlin
Button(
    title = "取消",
    variant = ButtonVariant.SECONDARY,
    modifier = Modifier.width(300.dp),
    onClick = {
        // 处理取消逻辑
    }
)
```

### 3. 危险操作按钮

用于危险操作，如退出登录、删除等。

```kotlin
Button(
    title = "退出登录",
    variant = ButtonVariant.DANGER,
    modifier = Modifier.width(300.dp),
    onClick = {
        // 处理退出逻辑
    }
)
```

### 4. 禁用状态

当按钮处于禁用状态时，自动应用禁用样式。

```kotlin
Button(
    title = "登录",
    enabled = false,
    modifier = Modifier.width(300.dp),
    onClick = {
        // 不会触发
    }
)
```

### 5. 宽度设置

- **固定宽度**: 使用 `Modifier.width(300.dp)` 设置固定宽度
- **填充宽度**: 使用 `Modifier.fillMaxWidth()` 填充父容器宽度
- **建议**: 登录页面的操作按钮建议使用固定宽度 `300.dp`

### 6. 高度设置

- 按钮高度固定为 `44.dp`，符合 Material Design 规范
- 除非有特殊需求，否则不建议修改高度

### 7. 使用 Composable 导入

```kotlin
import com.company.loginapp.ui.components.Button
import com.company.loginapp.ui.components.ButtonVariant
```

## 禁止事项

1. ❌ 不要使用非设计稿规范的颜色
2. ❌ 不要修改按钮高度（除非有特殊需求）
3. ❌ 不要在按钮文字中使用特殊符号（如图标）
4. ❌ 不要嵌套按钮
5. ❌ 不要同时使用 `SECONDARY` 和 `DANGER` 变体
6. ❌ 不要在 `modifier` 中重复设置 `height`（组件已设置默认值）

## 布局建议

### 垂直布局（Column）

```kotlin
Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
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
}
```

### 水平布局（Row）

```kotlin
Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    Button(
        title = "取消",
        variant = ButtonVariant.SECONDARY,
        modifier = Modifier.weight(1f),
        onClick = {}
    )
    
    Button(
        title = "确认",
        modifier = Modifier.weight(1f),
        onClick = {}
    )
}
```

## 可访问性

- 按钮文字应简洁明了，不超过 6 个汉字
- 避免使用纯符号或表情符号作为按钮文字
- 禁用状态应有明确的视觉反馈（灰色背景 + 灰色文字）

## Material Design 兼容性

- 使用 `MaterialTheme.typography.bodyMedium` 作为字体大小基准
- 符合 Material Design 按钮规范
- 支持 Material 3 设计语言

## 版本历史

- v1.0.0 (2026-02-27): 初始版本，基于设计稿创建
