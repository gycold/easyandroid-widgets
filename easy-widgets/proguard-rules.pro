# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#保留枚举
-keepclassmembers enum * {
 **[] $VALUES;
 public *;
}
#保留序列化的类
-keepclassmembers class * implements android.os.Parcelable {
 public static final android.os.Parcelable$Creator CREATOR;
}
#保留R文件的静态成员
-keepclassmembers class **.R$* {
 public static <fields>;
}
-keep class **.R$* { *; }

#保留自定义View的get和set方法
-keepclassmembers public class * extends android.view.View {
 void set*(***);
 *** get*();
}

# androidx的混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-keep class androidx.recyclerview.widget.**{*;}
-keep class androidx.viewpager2.widget.**{*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

-dontskipnonpubliclibraryclassmembers
-printconfiguration
-keep,allowobfuscation @interface androidx.annotation.Keep

-keep @androidx.annotation.Keep class *
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

#关闭优化
-dontoptimize
#保留注解
-keepattributes *Annotation*

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**