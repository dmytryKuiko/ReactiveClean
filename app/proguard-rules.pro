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
-dontwarn org.mockito.**
-dontwarn org.objenesis.**
-dontwarn retrofit2.**
-dontwarn okio.**
-dontwarn org.joda.**
-dontwarn okhttp3.**
-dontwarn net.bytebuddy.**
-dontwarn com.squareup.**
-dontwarn dagger.android.**

-repackageclasses
-allowaccessmodification
-flattenpackagehierarchy
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-dontoptimize
-dontpreverify


    -keep class retrofit.** { *; }
    -keepattributes Signature
    -keepattributes Exceptions
    -dontwarn java.lang.invoke.*
    -keep class com.example.dimi.reactiveclean.models.** { *; }
    -keep class com.example.dimi.reactiveclean.data.** { *; }
    -dontwarn retrofit.appengine.UrlFetchClient
    -keepclasseswithmembers class * {
        @retrofit.http.* <methods>;
    }
    -keepclassmembernames interface * {
        @retrofit.http.* <methods>;
    }
    -dontwarn retrofit2.Platform$Java8
