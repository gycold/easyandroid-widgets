## PrefixSuffixTextView

支持添加前缀、后缀、中间文字，可设置这些文字颜色

### 使用示例：
“您消费了：200元”，其中“200”是红色：
```xml
<com.easyandroid.widgets.textview.PrefixSuffixTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerInParent="true"
    app:easy_prefixText="您消费了 : "
    app:easy_prefixTextColor="#424242"
    app:easy_contentText="200"
    app:easy_contentTextColor="#ff0000"
    app:easy_suffixText=" 元"
    app:easy_suffixTextColor="#424242"/>
```