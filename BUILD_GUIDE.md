# دليل البناء المتقدم

معلومات متقدمة عن بناء وتطوير التطبيق.

---

## هيكل المشروع

```
AcademicServiceApp/
├── app/                          # مشروع التطبيق الرئيسي
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/             # كود Kotlin
│   │   │   ├── res/              # موارد (layouts, strings, etc)
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/          # اختبارات Android (على جهاز/محاكي)
│   │   └── test/                 # اختبارات الوحدة
│   ├── build.gradle              # إعدادات البناء للتطبيق
│   └── proguard-rules.pro        # قواعد ProGuard
├── gradle/                       # ملفات Gradle
├── build.gradle                  # الإعدادات الأساسية
├── settings.gradle               # إعدادات المشروع
├── gradle.properties             # خصائص Gradle
└── README.md                     # التوثيق الأساسي
```

---

## أنواع البناء (Build Types)

### Debug Build
```bash
./gradlew assembleDebug

# الخصائص:
# - قابل للتصحيح (debuggable)
# - بطء قليلاً
# - حجم أكبر
# - ملف: app/build/outputs/apk/debug/app-debug.apk
```

### Release Build
```bash
./gradlew assembleRelease

# الخصائص:
# - محسّن للأداء
# - أصغر حجم
# - مشفر ومُوقّع
# - ملف: app/build/outputs/apk/release/app-release.apk
```

### Profile Build
```bash
./gradlew assembleProfile

# الخصائص:
# - للاختبار والقياس
# - موازن بين Debug و Release
```

---

## متغيرات البيئة

### إعداد JAVA_HOME

#### Windows:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-11
echo %JAVA_HOME%
```

#### macOS/Linux:
```bash
export JAVA_HOME=/usr/libexec/java_home -v 11
echo $JAVA_HOME

# دائم (أضف إلى ~/.bash_profile أو ~/.zshrc):
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 11)' >> ~/.bash_profile
```

### إعداد ANDROID_HOME

#### Windows:
```cmd
setx ANDROID_HOME "%LOCALAPPDATA%\Android\Sdk"
echo %ANDROID_HOME%
```

#### macOS/Linux:
```bash
export ANDROID_HOME=$HOME/Android/Sdk
echo $ANDROID_HOME

# دائم:
echo 'export ANDROID_HOME=$HOME/Android/Sdk' >> ~/.bash_profile
echo 'export PATH=$ANDROID_HOME/platform-tools:$PATH' >> ~/.bash_profile
```

---

## الاختبارات (Testing)

### تشغيل جميع الاختبارات

```bash
# اختبارات الوحدة
./gradlew test

# اختبارات Android (تحتاج محاكي/جهاز)
./gradlew connectedAndroidTest

# كليهما
./gradlew test connectedAndroidTest
```

### تشغيل اختبار محدد

```bash
# اختبار فئة معينة
./gradlew test --tests StudentViewModelTest

# اختبار دالة معينة
./gradlew test --tests StudentViewModelTest.testGetAllStudents
```

### عرض نتائج الاختبارات

```bash
# ملف HTML للنتائج
# اختبارات الوحدة:
open app/build/reports/tests/testDebugUnitTest/index.html

# اختبارات Android:
open app/build/reports/androidTests/connected/index.html
```

---

## تحليل الكود (Lint)

### تشغيل Lint

```bash
# تحليل شامل
./gradlew lint

# تحليل محدد
./gradlew lint --info

# تخطي بعض التحذيرات
./gradlew lint -Plint-options="--disable MissingTranslation"
```

### عرض التقارير

```bash
# تقرير Lint HTML
open app/build/reports/lint-results.html

# تقرير XML
cat app/build/reports/lint-results.xml
```

---

## الأداء والحجم

### تقليل حجم APK

```gradle
// في app/build.gradle
android {
    buildTypes {
        release {
            // تقليل الموارد غير المستخدمة
            shrinkResources true
            
            // ضغط الكود
            minifyEnabled true
            
            // قواعد ProGuard
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'),
                         'proguard-rules.pro'
        }
    }
}
```

### قياس الأداء

```bash
# بناء مع Profiler
./gradlew clean build --profile

# عرض تقرير الأداء
# build/reports/profile-[timestamp].html
```

### تحليل المكتبات

```bash
# عرض حجم المكتبات
./gradlew dependencies

# حجم تفصيلي
./gradlew assemble --print-module-name
```

---

## التوقيع والتشفير

### إنشاء Keystore

```bash
# بدون كلمة سر (للاختبار فقط)
keytool -genkey -v -keystore release.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias academic_service \
  -storepass password \
  -keypass password \
  -dname "CN=Your Name, O=Your Org, C=SA"

# مع كلمة سر آمنة (الإنتاج)
keytool -genkey -v -keystore release.keystore \
  -keyalg RSA -keysize 4096 -validity 36500 \
  -alias academic_service
# سيُطلب إدخال كلمة السر بشكل تفاعلي
```

### التحقق من Keystore

```bash
keytool -list -v -keystore release.keystore
```

### توقيع APK يدويّاً

```bash
# إذا لم تكن قد وقعت عند البناء
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
  -keystore release.keystore \
  app-unsigned.apk academic_service

# التحقق من التوقيع
jarsigner -verify -verbose app-unsigned.apk
```

---

## إدارة الإصدارات

### نظام الإصدارات (Versioning)

```gradle
android {
    defaultConfig {
        versionCode = 1          // تزايد دائماً (للتحديثات)
        versionName = "1.0.0"    // للعرض على المستخدم
    }
}
```

### تحديث الإصدار

```gradle
// قبل الإصدار الجديد
android {
    defaultConfig {
        versionCode = 2          // زيادة رقم واحد
        versionName = "1.1.0"    // تحديث الإصدار
    }
}
```

---

## النشر على Google Play

### متطلبات Google Play

1. **التوقيع**: توقيع صحيح بـ Keystore
2. **الإصدار**: APK Release مُحسّن
3. **الرموز (Icons)**: صور عالية الجودة
4. **الوصف**: وصف التطبيق
5. **الموافقات**: قبول اتفاقيات Google

### خطوات النشر

1. **إعداد Google Play Console**: https://play.google.com/console
2. **إنشاء تطبيق جديد**
3. **بناء Release APK**:
```bash
./gradlew assembleRelease
```

4. **رفع الملف**:
   - في Google Play Console
   - اختر APK الذي بنيته
   - أكمل البيانات

---

## التطوير المتقدم

### استخدام Kotlin DSL

```kotlin
// في build.gradle.kts
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 34
    
    defaultConfig {
        applicationId = "com.academicservice"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }
    
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}
```

### استخدام Gradle Catalog

```toml
# gradle/libs.versions.toml
[versions]
kotlin = "1.8.0"
room = "2.5.2"

[libraries]
kotlin-stdlib = { module = "org.jetbrains.kotlin:kotlin-stdlib", version.ref = "kotlin" }
room-runtime = { module = "androidx.room:room-runtime", version.ref = "room" }

[plugins]
kotlin-android = { id = "kotlin-android", version.ref = "kotlin" }
```

---

## استكشاف الأخطاء

### مسح الذاكرة المؤقتة

```bash
# حذف جميع الملفات المُنشأة
./gradlew clean

# إعادة بناء من الصفر
./gradlew assembleDebug --no-build-cache

# حذف مجلد Gradle
rm -rf ~/.gradle
```

### التصحيح (Debugging)

```bash
# تشغيل مع تصحيح
./gradlew installDebug

# عرض السجلات
adb logcat

# تصفية السجلات
adb logcat | grep "academicservice"

# حفظ السجلات
adb logcat > logcat.txt
```

---

## أوامر Gradle المفيدة

```bash
# عرض جميع المهام المتاحة
./gradlew tasks

# عرض المشروع والمكتبات
./gradlew dependencies

# عرض التكوين
./gradlew properties

# تحديث المكتبات
./gradlew --refresh-dependencies

# بناء إضافي
./gradlew build

# حزم APK فقط
./gradlew assembleDebug

# تثبيت وتشغيل مباشرة
./gradlew installDebug

# حذف مخرجات البناء
./gradlew clean

# بناء مع تقارير الأداء
./gradlew build --profile
```

---

## أفضل الممارسات

### ✅ افعل:

1. **استخدم ProGuard** في Release
2. **اختبر على أجهزة حقيقية** قبل النشر
3. **أبقِ المكتبات محدثة**
4. **استخدم View Binding** بدل findViewById
5. **طبّق Coroutines** للعمليات غير المتزامنة
6. **وثّق الكود** بتعليقات واضحة

### ❌ تجنب:

1. **لا تستخدم API مُستنسخة**
2. **لا تحفظ كلمات السر** في الكود
3. **لا تستخدم Global Variables**
4. **لا تنسَ معالجة الأخطاء**
5. **لا تقرأ ملفات كبيرة في Thread الرئيسي**

---

## الموارد المفيدة

- [Android Developer Guide](https://developer.android.com)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Gradle Documentation](https://gradle.org/guides)
- [Google Play Policies](https://play.google.com/about/privacy-security-deception/malicious-behavior)

---

**آخر تحديث**: سبتمبر 2026
