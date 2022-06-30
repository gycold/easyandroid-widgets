## FlowLayout

Android流式布局，当一行没有足够的空间时，它可以动态计算子视图之间的间距，从而实现每行均匀放置。

![](FlowLayout.png)

### 说明

```xml
<com.easyandroid.widgets.label.FlowLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:flChildSpacing="auto"
    app:flChildSpacingForLastRow="align"
    app:flRowSpacing="8dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="SUN"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MON"/>

    <!--...-->

</com.easyandroid.widgets.label.FlowLayout>
```


## Attributes

| 属性                | 类型                       | 描述                                                                                                                                          |
|--------------------------|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| flFlow                   | boolean                      | `true` 允许子视图流动分布，`false` 限制所有子视图在一行， 默认是 `true`                                                                                       |
| flChildSpacing           | `auto`/dimension             | 子视图之间的水平间距，自动或固定大小，默认值为0dp                                                                                                             |
| flChildSpacingForLastRow | `auto`/`align`/<br>dimension | 最后一行的子视图之间的水平间距，自动对齐或固定大小，如果未设置，将使用子间距                                                                                       |
| flRowSpacing             | `auto`/dimension             | 行之间的垂直间距，自动或固定大小，默认值为0dp                                                                                                                |
| flRtl                    | boolean                      | `true` 布局方向从右到左， `false` 布局方向从左到右，默认值 `false`                                                                                           |
| flMaxRows                | integer                      | 按行数计算的FlowLayout的最大高度                                                                                                                          |

`auto` 表示的是，实际间距是根据`FlowLayout`的大小和子视图（或行）的数量计算的，以便子视图（或行）均匀放置。
`align` 在 `childSpacingForLastRow` 中表示最后一行中的子视图的水平间距与上一行中使用的间距保持相同，如果`FlowLayout`中只有一行，并且`childSpacingForLastRow`设置为`align`，则忽略该值，并使用`childSpacing`计算实际间距

