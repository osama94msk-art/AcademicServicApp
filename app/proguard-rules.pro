# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers class * {
    @androidx.room.* <fields>;
}

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# OkHttp
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Kotlin
-keepclassmembers class kotlin.Metadata {
    *** invoke(...);
}
-keep class kotlin.reflect.jvm.internal.** { *; }

# AndroidX
-keep class androidx.** { *; }
-keepnames class androidx.** { *; }

# Material Design
-keep class com.google.android.material.** { *; }
-keepnames class com.google.android.material.** { *; }

# Serialization
-keepclassmembers class com.academicservice.data.models.** {
    public <init>();
    public <methods>;
    public <fields>;
}

# Debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
