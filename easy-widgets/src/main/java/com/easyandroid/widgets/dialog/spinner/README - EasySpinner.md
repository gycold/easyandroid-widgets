## EasySpinner

快速实现一个Spinner，快速开发

### 注意
`arrowDrawable`需要和`arrowTint`配合使用，后者用来渲染颜色，设置为图片的颜色即可。
同时，`arrowDrawable`若可为旋转，需要的drawable资源为：
```xml
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
        android:drawable="@mipmap/ic_smart_arrow"
        android:fromDegrees="0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toDegrees="180" />
```
图片箭头初始向下。
仅使用`arrowTint`可直接更改默认箭头颜色。

### 用法：
```xml
<com.easyandroid.widgets.dialog.spinner.EasySpinner
        android:id="@+id/easy_spinner"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="8dp"
        android:layout_marginRight="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:layout_constraintVertical_bias="0.0" />

    <com.easyandroid.widgets.dialog.spinner.EasySpinner
        android:id="@+id/easySpinnerXml"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="8dp"
        android:layout_marginRight="16dp"
        app:entries="@array/courses"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView3"
        app:layout_constraintVertical_bias="0.0" />

string.xml:
<resources>
    <string-array name="courses">
        <item>English</item>
        <item>Chinese</item>
        <item>Math</item>
    </string-array>
<resources>

    <com.easyandroid.widgets.dialog.spinner.EasySpinner
        android:id="@+id/tinted_easy_spinner"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="16dp"
        android:layout_marginRight="16dp"
        app:spinner_arrowTint="#999999"
        app:spinner_backgroundSelector="@drawable/background_selector"
        app:spinner_item_backgroundSelector="@drawable/background_selector"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView2"
        app:layout_constraintVertical_bias="0.0"
        app:textTint="@color/colorPrimary" />
```

Java中：

```xml
        EasySpinner spinner = findViewById(R.id.tinted_easy_spinner);
        List<Person> people = new ArrayList<>();

        people.add(new Person("Tony", "Stark"));
        people.add(new Person("Steve", "Rogers"));
        people.add(new Person("Bruce", "Banner"));

        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<Person>() {
            @Override
            public Spannable format(Person person) {
                return new SpannableString(person.getName() + " " + person.getSurname());
            }
        };

        spinner.setSpinnerTextFormatter(textFormatter);
        spinner.setSelectedTextFormatter(textFormatter);
        spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(EasySpinner parent, View view, int position, long id) {
                Person person = (Person) spinner.getSelectedItem();
                Toast.makeText(MainActivity.this, "Selected: " + person.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        spinner.attachDataSource(people);
```

### 属性：

| name                      | type      | info                                                   |
|------------------------   |-----------|--------------------------------------------------------|
| spinner_arrowTint                 | color     | 设置下拉箭头颜色                                          |
| spinner_hideArrow                 | boolean   | 设置是否显示下拉箭头                                       |
| spinner_arrowDrawable             | reference | 设置下拉箭头的drawable                                    |
| spinner_textTint                  | color     | 设置字体颜色                                              |
| spinner_title_margin              | dimension | 标题与下拉菜单栏间距                                      |
| spinner_backgroundSelector        | integer   | spinner本身的背景                                   |
| spinner_item_backgroundSelector   | integer   | 为下拉菜单的每一列设置背景                                   |
| spinner_popupTextAlignment        | enum      | 设置菜单文字的对齐方式                                      |
| spinner_entries                   | reference | 设置字符串集合                                             |
