## LinkedTextView

一个支持点击文字、展开文字、收起文字、点击跳转网页的TextView
点击文字颜色、展开/收起两个字的颜色设置都支持

![使用示例](sample.jpg)



-----
### 使用示例：

1. **正常带链接和@用户，没有展开和收回功能：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_01"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp" />
```



2. **正常带链接，不带@用户，有展开和收回功能，有切换动画，有展开和回收的动作监听：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_02"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true"
    app:ltv_need_mention="false" />
```
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_02_1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true"
    app:ltv_need_mention="false" />
```



3. **正常不带链接，不带@用户，有展开和收回功能，有切换动画：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_03"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true"
    app:ltv_need_link="false"
    app:ltv_need_mention="false" />
```



4. **正常带链接和@用户，有展开和收回功能，有切换动画：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_04"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true" />
```



5. **正常带链接和@用户，有展开和收回功能，没有切换动画：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_05"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true" />
```



6. **正常带链接和@用户，有展开，没有收回功能：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_06"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="5dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="false"
    app:ltv_need_expand="true" />
```



7. **正常带链接和@用户，有展开，有收回功能，带附加内容(比如时间)：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_07"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="4dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true" />
```



8. **正常带链接和@用户，有展开，没有收回功能，带附加内容(比如时间)：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_08"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="5dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="false"
    app:ltv_need_expand="true" />
```



9. **正常带链接和@用户，有展开，有收回功能，有'展开'和'收起'始终靠右显示的功能：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_09"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="5dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true"
    app:ltv_need_self="true" />
```



10. **正常带链接和@用户，有展开，有收回功能，带自定义规则（解析`标题`(规则)并处理，例如对一些字段进行自定义处理，比如文字中的'--习大大' 和 'Github地址'）：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_10"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:lineSpacingExtra="5dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_expand="true"
    app:ltv_need_self="true" />
```



11. **正常带链接和@用户，有展开，有收回功能，文本中链接不转换成网页链接的文本提示，监听初始化完成：**
```xml
<com.easyandroid.widgets.textview.link.LinkedTextView
    android:id="@+id/ltv_11"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" 
    android:layout_margin="15dp"
    android:lineSpacingExtra="5dp"
    android:textSize="14sp"
    app:ltv_max_line="5"
    app:ltv_need_contract="true"
    app:ltv_need_convert_url="false"
    app:ltv_need_expand="true" />
```


