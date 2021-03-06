## ShapeView

形状封装的TextView、Button、CheckBox、EditText、ImageView、RadioButton、View

### 一、代码演示：

```java
ShapeButton shapeButton = findViewById(R.id.btn_main_test);
shapeButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    
        shapeButton.getShapeDrawableBuilder()
                .setSolidColor(0xFF000000)
                .setStrokeColor(0xFF5A8DDF)
                // 注意：最后需要调用一下 intoBackground 方法才能生效
                .intoBackground();

        shapeButton.getTextColorBuilder()
                .setTextColor(0xFFFFFFFF)
                // 注意：最后需要调用一下 intoTextColor 方法才能生效
                .intoTextColor();

        shapeButton.setText("颜色已经改变啦");
    }
});
```

### 二、布局属性：

```xml
<resources>

  <!-- Shape 形状（默认是矩形） -->
  <attr name="shape">
    <!-- 矩形 -->
    <enum name="rectangle" value="0" />
    <!-- 椭圆形 -->
    <enum name="oval" value="1" />
    <!-- 线条 -->
    <enum name="line" value="2" />
    <!-- 圆环 -->
    <enum name="ring" value="3" />
  </attr>
  <!-- Shape 宽度 -->
  <attr name="shape_width" format="dimension" />
  <!-- Shape 高度 -->
  <attr name="shape_height" format="dimension" />

  <!-- 填充色（默认状态） -->
  <attr name="shape_solidColor" format="color" />
  <!-- 填充色（按下状态） -->
  <attr name="shape_solidPressedColor" format="color" />
  <!-- 填充色（选中状态） -->
  <attr name="shape_solidCheckedColor" format="color" />
  <!-- 填充色（禁用状态） -->
  <attr name="shape_solidDisabledColor" format="color" />
  <!-- 填充色（焦点状态） -->
  <attr name="shape_solidFocusedColor" format="color" />
  <!-- 填充色（选择状态） -->
  <attr name="shape_solidSelectedColor" format="color" />

  <!-- 圆角大小 -->
  <attr name="shape_radius" format="dimension" />
  <!-- 左上角的圆角大小 -->
  <attr name="shape_topLeftRadius" format="dimension" />
  <!-- 右上角的圆角大小 -->
  <attr name="shape_topRightRadius" format="dimension" />
  <!-- 左下角的圆角大小 -->
  <attr name="shape_bottomLeftRadius" format="dimension" />
  <!-- 右下角的圆角大小 -->
  <attr name="shape_bottomRightRadius" format="dimension" />

  <!-- 渐变色起始颜色 -->
  <attr name="shape_startColor" format="color" />
  <!-- 渐变色中间颜色（可不设置） -->
  <attr name="shape_centerColor" format="color" />
  <!-- 渐变色结束颜色 -->
  <attr name="shape_endColor" format="color" />

  <!-- 边框渐变色起始颜色 -->
  <attr name="shape_strokeStartColor" format="color" />
  <!-- 边框渐变色中间颜色（可不设置） -->
  <attr name="shape_strokeCenterColor" format="color" />
  <!-- 边框渐变色结束颜色 -->
  <attr name="shape_strokeEndColor" format="color" />

  <!-- 是否将用于缩放渐变 -->
  <attr name="shape_useLevel" format="boolean" />
  <!-- 渐变角度（仅用于线性渐变。必须是 0-315 范围内的值，并且是 45 的倍数） -->
  <attr name="shape_angle" format="float" />
  <!-- 渐变类型（默认类型是线性渐变） -->
  <attr name="shape_gradientType">
    <!-- 线性渐变 -->
    <enum name="linear" value="0" />
    <!-- 径向渐变 -->
    <enum name="radial" value="1" />
    <!-- 扫描渐变 -->
    <enum name="sweep"  value="2" />
  </attr>
  <!-- 渐变中心 X 点坐标的相对位置（默认值为 0.5）-->
  <attr name="shape_centerX" format="float|fraction" />
  <!-- 渐变中心 Y 点坐标的相对位置（默认值为 0.5）-->
  <attr name="shape_centerY" format="float|fraction" />
  <!-- 渐变色半径（仅用于径向渐变） -->
  <attr name="shape_gradientRadius" format="float|fraction|dimension" />

  <!-- 边框色（默认状态） -->
  <attr name="shape_strokeColor" format="color" />
  <!-- 边框色（按下状态） -->
  <attr name="shape_strokePressedColor" format="color" />
  <!-- 边框色（选中状态） -->
  <attr name="shape_strokeCheckedColor" format="color" />
  <!-- 边框色（禁用状态） -->
  <attr name="shape_strokeDisabledColor" format="color" />
  <!-- 边框色（焦点状态） -->
  <attr name="shape_strokeFocusedColor" format="color" />
  <!-- 边框色（选择状态） -->
  <attr name="shape_strokeSelectedColor" format="color" />

  <!-- 边框宽度 -->
  <attr name="shape_strokeWidth" format="dimension" />
  <!-- 边框虚线宽度（为 0 就是实线，大于 0 就是虚线） -->
  <attr name="shape_dashWidth" format="dimension" />
  <!-- 边框虚线间隔（虚线与虚线之间的间隔） -->
  <attr name="shape_dashGap" format="dimension" />

  <!-- 阴影大小 -->
  <attr name="shape_shadowSize" format="dimension" />
  <!-- 阴影颜色 -->
  <attr name="shape_shadowColor" format="color" />
  <!-- 阴影水平偏移 -->
  <attr name="shape_shadowOffsetX" format="dimension" />
  <!-- 阴影垂直偏移 -->
  <attr name="shape_shadowOffsetY" format="dimension" />

  <!-- 内环的半径（仅在 shape="ring" 生效） -->
  <attr name="shape_innerRadius" format="dimension" />
  <!-- 内环的半径比率（仅在 shape="ring" 生效），计算公式：整个圆环 / innerRadiusRatio = innerRadius -->
  <attr name="shape_innerRadiusRatio" format="float" />
  <!-- 外环的厚度（仅在 shape="ring" 生效） -->
  <attr name="shape_thickness" format="dimension" />
  <!-- 外环的厚度比率（仅在 shape="ring" 生效），计算公式：整个圆环 / thicknessRatio = thickness -->
  <attr name="shape_thicknessRatio" format="float" />

  <!-- 线条重心（仅在 shape="line" 生效） -->
  <attr name="shape_lineGravity">
    <flag name="top" value="0x30" />
    <flag name="bottom" value="0x50" />
    <flag name="left" value="0x03" />
    <flag name="right" value="0x05" />
    <flag name="start" value="0x00800003" />
    <flag name="end" value="0x00800005" />
    <flag name="center" value="0x11" />
  </attr>

  <!-- 文本色（默认状态） -->
  <attr name="shape_textColor" format="color" />
  <!-- 文本色（按下状态） -->
  <attr name="shape_textPressedColor" format="color" />
  <!-- 文本色（选中状态） -->
  <attr name="shape_textCheckedColor" format="color" />
  <!-- 文本色（禁用状态） -->
  <attr name="shape_textDisabledColor" format="color" />
  <!-- 文本色（焦点状态） -->
  <attr name="shape_textFocusedColor" format="color" />
  <!-- 文本色（选择状态） -->
  <attr name="shape_textSelectedColor" format="color" />

  <!-- 文本渐变色起始颜色 -->
  <attr name="shape_textStartColor" format="color" />
  <!-- 文本渐变色中间颜色（可不设置） -->
  <attr name="shape_textCenterColor" format="color" />
  <!-- 文本渐变色结束颜色 -->
  <attr name="shape_textEndColor" format="color" />
  <!-- 文本渐变方向（默认类型是水平渐变） -->
  <attr name="shape_textGradientOrientation">
    <!-- 水平渐变 -->
    <enum name="horizontal" value="0" />
    <!-- 垂直渐变 -->
    <enum name="vertical" value="1" />
  </attr>

  <!-- 文本边框颜色 -->
  <attr name="shape_textStrokeColor" format="color" />
  <!-- 文本边框大小 -->
  <attr name="shape_textStrokeSize" format="dimension" />

  <!-- CheckBox 或者 RadioButton 图标（默认状态） -->
  <attr name="shape_buttonDrawable" format="reference" />
  <!-- CheckBox 或者 RadioButton 图标（按下状态） -->
  <attr name="shape_buttonPressedDrawable" format="reference" />
  <!-- CheckBox 或者 RadioButton 图标（选中状态） -->
  <attr name="shape_buttonCheckedDrawable" format="reference" />
  <!-- CheckBox 或者 RadioButton 图标（禁用状态） -->
  <attr name="shape_buttonDisabledDrawable" format="reference" />
  <!-- CheckBox 或者 RadioButton 图标（焦点状态） -->
  <attr name="shape_buttonFocusedDrawable" format="reference" />
  <!-- CheckBox 或者 RadioButton 图标（选择状态） -->
  <attr name="shape_buttonSelectedDrawable" format="reference" />

</resources>
```

### 三、布局演示：
![](pictures/dynamic_figure.jpg)
![](pictures/shape_gradient.jpg)
![](pictures/shape_line.jpg)
![](pictures/shape_oval.jpg)
![](pictures/shape_rectangle.jpg)
![](pictures/shape_rectangle_gradient.jpg)
![](pictures/shape_rectangle_round.jpg)
![](pictures/shape_ring.jpg)
![](pictures/shape_select_background.jpg)
![](pictures/shape_select_compound_button.jpg)
![](pictures/shape_select_text_gradient.jpg)
![](pictures/shape_shadow_background.jpg)



```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_gravity="center"
    android:orientation="vertical"
    tools:context=".MainActivity">
    
    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center_horizontal"
            android:orientation="vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="设置状态选择器"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="填充色按压效果"
                android:textColor="@android:color/white"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_solidColor="#5A8DDF"
                app:shape_solidPressedColor="#AA5A8DDF" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:enabled="false"
                android:gravity="center"
                android:padding="10dp"
                android:text="填充色禁用效果"
                android:textColor="@android:color/white"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_solidColor="#5A8DDF"
                app:shape_solidDisabledColor="#BBBBBB" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="边框色按压效果"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_solidColor="@android:color/transparent"
                app:shape_solidPressedColor="#5A8DDF"
                app:shape_strokeColor="#5A8DDF"
                app:shape_strokeWidth="1dp"
                app:shape_textColor="#5A8DDF"
                app:shape_textPressedColor="@android:color/white" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:enabled="false"
                android:gravity="center"
                android:padding="10dp"
                android:text="边框色禁用效果"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_strokeColor="#5A8DDF"
                app:shape_strokeDisabledColor="#BBBBBB"
                app:shape_strokeWidth="1dp"
                app:shape_textColor="@android:color/black"
                app:shape_textDisabledColor="#BBBBBB" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:id="@+id/btn_main_test"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="点我动态改变颜色"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_strokeColor="#000000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="透明填充色按压效果"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_solidPressedColor="#AA5A8DDF"
                app:shape_strokePressedColor="#000000"
                app:shape_strokeWidth="1dp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="设置渐变色效果"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="填充色渐变效果"
                android:textColor="@android:color/white"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_endColor="#ED58FF"
                app:shape_radius="20dp"
                app:shape_solidPressedColor="#5A8DDF"
                app:shape_startColor="#49DAFA" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="边框色渐变效果"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_strokeEndColor="#f08833"
                app:shape_strokePressedColor="@android:color/black"
                app:shape_strokeStartColor="#fefa54"
                app:shape_strokeWidth="2dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeButton
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="组合渐变效果"
                android:textColor="@android:color/white"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_endColor="#ED58FF"
                app:shape_radius="20dp"
                app:shape_solidPressedColor="#5A8DDF"
                app:shape_startColor="#49DAFA"
                app:shape_strokeEndColor="#f08833"
                app:shape_strokePressedColor="@android:color/black"
                app:shape_strokeStartColor="#fefa54"
                app:shape_strokeWidth="2dp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="设置阴影效果"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="默认阴影颜色"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="10dp"
                app:shape_shadowColor="#20000000"
                app:shape_shadowSize="10dp"
                app:shape_solidColor="#FFFFFF" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="修改阴影颜色"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="10dp"
                app:shape_shadowColor="#AA5A8DDF"
                app:shape_shadowSize="10dp"
                app:shape_solidColor="#FFFFFF" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="阴影偏移效果（右下）"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="10dp"
                app:shape_shadowColor="#50000000"
                app:shape_shadowOffsetX="8dp"
                app:shape_shadowOffsetY="8dp"
                app:shape_shadowSize="10dp"
                app:shape_solidColor="#FFFFFF" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="阴影偏移效果（左上）"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="10dp"
                app:shape_shadowColor="#50000000"
                app:shape_shadowOffsetX="-8dp"
                app:shape_shadowOffsetY="-8dp"
                app:shape_shadowSize="10dp"
                app:shape_solidColor="#FFFFFF" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:clickable="true"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="阴影偏移效果（不规则圆角）"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_bottomLeftRadius="15dp"
                app:shape_bottomRightRadius="20dp"
                app:shape_shadowColor="#50000000"
                app:shape_shadowOffsetX="-8dp"
                app:shape_shadowOffsetY="-8dp"
                app:shape_shadowSize="10dp"
                app:shape_solidColor="#FFFFFF"
                app:shape_topLeftRadius="5dp"
                app:shape_topRightRadius="10dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="边框和阴影效果"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="10dp"
                app:shape_shadowColor="#FF0000"
                app:shape_shadowSize="10dp"
                app:shape_strokeColor="#000000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:paddingTop="30dp"
                android:paddingBottom="30dp"
                android:text="渐变色和阴影效果"
                android:textColor="@android:color/white"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_endColor="#f08833"
                app:shape_radius="10dp"
                app:shape_shadowColor="#99000000"
                app:shape_shadowSize="10dp"
                app:shape_startColor="#fefa54" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="文本渐变色效果"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="文本水平渐变效果"
                android:textSize="18sp"
                app:shape_textEndColor="#ED58FF"
                app:shape_textGradientOrientation="horizontal"
                app:shape_textStartColor="#49DAFA" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="文本垂直渐变效果"
                android:textSize="18sp"
                app:shape_textEndColor="#ED58FF"
                app:shape_textGradientOrientation="vertical"
                app:shape_textStartColor="#49DAFA" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:id="@+id/test"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                app:shape_textColor="#5A8DDF"
                android:text="文本边框颜色效果"
                android:textSize="18sp"
                app:shape_textEndColor="#f08833"
                app:shape_textGradientOrientation="horizontal"
                app:shape_textStartColor="#fefa54"
                app:shape_textStrokeColor="#000000"
                app:shape_textStrokeSize="2sp" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="Check 控件图标状态"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeCheckBox
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:background="@null"
                android:gravity="center"
                android:padding="10dp"
                android:text="我是自定义按钮样式的 CheckBox"
                android:textSize="14sp"
                app:shape_buttonCheckedDrawable="@drawable/checkbox_checked_ic"
                app:shape_buttonDisabledDrawable="@drawable/checkbox_disable_ic"
                app:shape_buttonDrawable="@drawable/checkbox_normal_ic" />

            <com.easyandroid.widgets.textview.shape.view.ShapeRadioButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:background="@null"
                android:gravity="center"
                android:padding="10dp"
                android:text="我是自定义按钮样式的 RadioButton"
                android:textSize="14sp"
                app:shape_buttonCheckedDrawable="@drawable/radiobutton_checked_ic"
                app:shape_buttonDisabledDrawable="@drawable/radiobutton_disable_ic"
                app:shape_buttonDrawable="@drawable/radiobutton_normal_ic" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="矩形（边框+填充）"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="矩形实线边框内部无填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="矩形虚线边框内部无填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_dashGap="5dp"
                app:shape_dashWidth="10dp"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="矩形实线边框-内部填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_solidColor="#ff00ffff"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="矩形虚线边框-内部填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_dashGap="5dp"
                app:shape_dashWidth="10dp"
                app:shape_solidColor="#ff00ffff"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形-只有边框"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="5dp"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形-只有内部填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="5dp"
                app:shape_solidColor="#8000ff00" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形-有边框有填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="5dp"
                app:shape_solidColor="#8000ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形-左边圆角为一个半圆弧"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_bottomLeftRadius="20dp"
                app:shape_solidColor="#8000ff00"
                app:shape_topLeftRadius="20dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形-左右两边都是半圆弧"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_solidColor="#8000ff00" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆角矩形-左右两边都是半圆弧-带边框"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_radius="20dp"
                app:shape_solidColor="#8000ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:padding="10dp"
                android:text="渐变效果（以矩形为例）"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="垂直线性渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_angle="-45"
                app:shape_centerColor="#8000ff00"
                app:shape_centerX="0.5"
                app:shape_centerY="0.4"
                app:shape_endColor="#1000ff00"
                app:shape_startColor="#ff00ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="水平线性渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_angle="0"
                app:shape_centerColor="#8000ff00"
                app:shape_centerX="0.5"
                app:shape_centerY="0.5"
                app:shape_endColor="#ff00ff00"
                app:shape_startColor="#1000ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="对角线线性渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_angle="45"
                app:shape_centerColor="#8000ff00"
                app:shape_centerX="0.5"
                app:shape_centerY="0.5"
                app:shape_endColor="#1000ff00"
                app:shape_startColor="#ff00ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="径向渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_angle="0"
                app:shape_centerX="0.5"
                app:shape_centerY="0.5"
                app:shape_endColor="#ff00ff00"
                app:shape_gradientRadius="20dp"
                app:shape_gradientType="radial"
                app:shape_startColor="#0000ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="扫描渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="rectangle"
                app:shape_angle="0"
                app:shape_centerX="0.5"
                app:shape_centerY="0.5"
                app:shape_endColor="#0000ff00"
                app:shape_gradientType="sweep"
                app:shape_startColor="#ff00ff00"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆（边框+填充+渐变）"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆-边框"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="oval"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆-填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="oval"
                app:shape_solidColor="#800000ff" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆-边框填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="oval"
                app:shape_solidColor="#800000ff"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="线性渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="oval"
                app:shape_angle="-90"
                app:shape_centerColor="#80ff0000"
                app:shape_centerX="0.5"
                app:shape_centerY="0.8"
                app:shape_endColor="#ffff0000"
                app:shape_gradientType="linear"
                app:shape_startColor="#00ff0000" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="径向渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="oval"
                app:shape_centerColor="#80ff0000"
                app:shape_centerX="0.5"
                app:shape_centerY="0.5"
                app:shape_endColor="#10ff0000"
                app:shape_gradientRadius="80dp"
                app:shape_gradientType="radial"
                app:shape_startColor="#ffff0000" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="扫描渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="oval"
                app:shape_centerColor="#80ff0000"
                app:shape_centerX="0.5"
                app:shape_centerY="0.6"
                app:shape_endColor="#20ff0000"
                app:shape_gradientRadius="20dp"
                app:shape_gradientType="sweep"
                app:shape_startColor="#ffff0000" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆环（边框+填充+渐变）"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="环内填充"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="ring"
                app:shape_innerRadiusRatio="4"
                app:shape_solidColor="#80ff0000"
                app:shape_thicknessRatio="4" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆环边框"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="ring"
                app:shape_innerRadiusRatio="4"
                app:shape_strokeColor="#ffff00ff"
                app:shape_strokeWidth="2dp"
                app:shape_thicknessRatio="4" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="圆环边框"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="ring"
                app:shape_innerRadiusRatio="4"
                app:shape_solidColor="#80ff0000"
                app:shape_strokeColor="#ffff00ff"
                app:shape_strokeWidth="2dp"
                app:shape_thicknessRatio="4" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="线性渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="ring"
                app:shape_angle="45"
                app:shape_centerColor="#80ff0000"
                app:shape_endColor="#ffff0000"
                app:shape_gradientType="linear"
                app:shape_innerRadiusRatio="4"
                app:shape_startColor="#00ff0000"
                app:shape_thicknessRatio="4" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="扫描渐变"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="ring"
                app:shape_centerColor="#80ff0000"
                app:shape_endColor="#00ff0000"
                app:shape_gradientType="sweep"
                app:shape_innerRadiusRatio="4"
                app:shape_startColor="#ffff0000"
                app:shape_thicknessRatio="4" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:padding="10dp"
                android:text="线（实线+虚线）"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:textStyle="bold" />

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="实线"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="line"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp"/>

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="虚线"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape="line"
                app:shape_dashGap="5dp"
                app:shape_dashWidth="10dp"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="1dp"/>

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="线在字的左边"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape_lineGravity="start"
                app:shape="line"
                app:shape_strokeColor="#ffff0000"
                app:shape_strokeWidth="2dp"/>

            <com.easyandroid.widgets.textview.shape.view.ShapeTextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:gravity="center"
                android:padding="10dp"
                android:text="改变线的大小和颜色"
                android:textColor="@android:color/black"
                android:textSize="14sp"
                app:shape_lineGravity="bottom"
                app:shape="line"
                app:shape_strokeColor="#ffff00ff"
                app:shape_strokeWidth="3dp" />

        </LinearLayout>

    </androidx.core.widget.NestedScrollView>

</LinearLayout>
```

### 四、目前支持这些属性的控件：

    * View 的子类：ShapeView、ShapeTextView、ShapeButton、ShapeImageView、ShapeRadioButton、ShapeCheckBox、ShapeEditText
    
    * ViewGroup 的子类：ShapeLinearLayout、ShapeFrameLayout、ShapeRelativeLayout、ShapeConstraintLayout、ShapeRecyclerView、ShapeRadioGroup
