## DashLine

### 使用说明：
`lineColor`：线条颜色，必需。
`dashOrientation`：0或1,0水平方向，1竖直方向，默认0，非必需。
`lineHeight`：线段的高度，受布局高度限制，非必需。
`lineWidth`：线段的宽度，受布局宽度限制，非必需。
`dashWidth`：虚线点之间的间距，非必需。

```xml
<com.easyandroid.widgets.line.DashLine
        android:layout_marginLeft="40dp"
        android:layout_width="wrap_content"
        android:layout_height="300dp"
        app:dashWidth="2dp"
        app:lineWidth="4dp"
        app:lineColor="@android:color/holo_green_light"
        app:dashOrientation="0"
        app:lineHeight="1dp"/>
```