# دليل التثبيت الشامل - تطبيق إدارة الخدمات الأكاديمية

## المتطلبات الأساسية

### متطلبات النظام
- **نظام التشغيل**: Windows 10+, macOS 10.14+, Linux
- **الذاكرة**: 8 GB RAM على الأقل
- **مساحة التخزين**: 10 GB لأدوات التطوير
- **المتصفح**: Chrome أو Firefox لعرض المستندات

### متطلبات البرمجيات
- **JDK 11 أو أحدث**: https://www.oracle.com/java/technologies/downloads/
- **Android Studio**: https://developer.android.com/studio
- **Android SDK**: API 34 (سيتم تحميله تلقائياً)
- **Gradle**: 8.0 أو أحدث (مدرج مع Android Studio)

---

## خطوات التثبيت الكاملة

### الخطوة 1: تثبيت Java Development Kit (JDK)

#### على Windows:
1. انتقل إلى https://www.oracle.com/java/technologies/downloads/
2. اختر **JDK 11 LTS** أو أحدث
3. حمّل نسخة Windows
4. شغّل المثبت واتبع التعليمات
5. تحقق من التثبيت:
```cmd
java -version
javac -version
```

#### على macOS:
```bash
# استخدام Homebrew
brew install openjdk@11

# تعيين متغير البيئة
echo 'export PATH="/usr/local/opt/openjdk@11/bin:$PATH"' >> ~/.bash_profile
source ~/.bash_profile
```

#### على Linux:
```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install openjdk-11-jdk

# Fedora
sudo dnf install java-11-openjdk
```

### الخطوة 2: تثبيت Android Studio

1. انتقل إلى https://developer.android.com/studio
2. حمّل Android Studio الإصدار الأخير
3. شغّل المثبت وانتظر اكتمال التثبيت
4. عند الفتح لأول مرة:
   - اختر "Do not import settings"
   - اختر "Install type: Standard"
   - وافق على جميع التراخيص
   - انتظر تحميل المكونات الإضافية

### الخطوة 3: إعداد Android SDK

1. في Android Studio، اذهب إلى **Tools → SDK Manager**
2. تأكد من تثبيت الإصدارات التالية:
   - **API Level 34** (أحدث)
   - **Build Tools 34.0.0+**
   - **Android Emulator**
   - **Android SDK Platform-Tools**
   - **Android SDK Tools**

3. اضغط **Apply** و **OK** وانتظر الانتهاء

### الخطوة 4: استنساخ المشروع

#### باستخدام Git:
```bash
# إذا لم تكن قد ثبتت Git
# Windows: https://git-scm.com/download/win
# macOS: brew install git
# Linux: sudo apt-get install git

# استنساخ المشروع
git clone https://github.com/yourusername/AcademicServiceApp.git
cd AcademicServiceApp
```

#### أو تحميل الملف المضغوط:
1. اضغط على **Code** و **Download ZIP**
2. فك ضغط الملف في مجلد على جهازك
3. افتح Terminal/Command Prompt في المجلد

### الخطوة 5: فتح المشروع في Android Studio

1. في Android Studio، اختر **File → Open**
2. انتقل إلى مجلد `AcademicServiceApp` واختره
3. اضغط **OK** وانتظر Gradle indexing

### الخطوة 6: بناء المشروع

```bash
# من Terminal في Android Studio أو من مجلد المشروع
./gradlew build

# أو على Windows
gradlew.bat build
```

### الخطوة 7: تشغيل التطبيق

#### الخيار 1: على محاكي (Emulator)
1. في Android Studio، اختر **Tools → AVD Manager**
2. اضغط **Create Virtual Device**
3. اختر جهاز (مثل Pixel 4)
4. اختر API Level 34
5. أتمم الإعداد واضغط **Finish**
6. اضغط على زر التشغيل الأخضر
7. انتظر تشغيل المحاكي
8. في Android Studio، اضغط **Run → Run 'app'**

#### الخيار 2: على جهاز فعلي
1. فعّل **Developer Mode** على الجهاز:
   - Settings → About Phone → اضغط Build Number 7 مرات
   - قد تحتاج إلى إدخال PIN

2. فعّل **USB Debugging**:
   - Settings → Developer Options → USB Debugging (ON)

3. وصّل الجهاز بالكمبيوتر عبر USB

4. في Android Studio:
   - اختر Run → Run 'app'
   - اختر جهازك من القائمة
   - اضغط OK

---

## بناء ملف APK

### بناء APK للتطوير (Debug)

```bash
# من Terminal
./gradlew assembleDebug

# الملف الناتج:
# app/build/outputs/apk/debug/app-debug.apk
```

**حجم الملف**: ~50 MB تقريباً

### بناء APK للإنتاج (Release)

#### 1. إنشاء مفتاح توقيع (Keystore)

```bash
# يتم هذا مرة واحدة فقط
keytool -genkey -v -keystore release.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias academic_service

# ستُطلب منك كلمة مرور وبيانات إضافية
```

#### 2. تكوين Gradle

عدّل `app/build.gradle`:

```gradle
android {
    signingConfigs {
        release {
            storeFile file('release.keystore')
            storePassword 'your_password'
            keyAlias 'academic_service'
            keyPassword 'your_password'
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            shrinkResources true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

#### 3. بناء APK Release

```bash
./gradlew assembleRelease

# الملف الناتج:
# app/build/outputs/apk/release/app-release.apk
```

---

## التحقق من التثبيت

### اختبارات للتأكد من أن كل شيء يعمل:

```bash
# 1. التحقق من Java
java -version
# يجب أن تشاهد: openjdk 11.x.x أو أحدث

# 2. التحقق من Gradle
./gradlew --version
# يجب أن تشاهد: Gradle 8.0 أو أحدث

# 3. التحقق من البناء
./gradlew clean build
# يجب أن ينتهي بـ: BUILD SUCCESSFUL
```

---

## حل المشاكل الشائعة

### مشكلة 1: "SDK not found"
**الحل**:
```bash
# تعيين ANDROID_HOME
# Windows:
setx ANDROID_HOME "C:\Users\YourUsername\AppData\Local\Android\Sdk"

# macOS/Linux:
echo 'export ANDROID_HOME=$HOME/Android/Sdk' >> ~/.bashrc
source ~/.bashrc
```

### مشكلة 2: "Gradle build failed"
```bash
# تنظيف والبناء من جديد
./gradlew clean build --refresh-dependencies
```

### مشكلة 3: "Could not find tools.jar"
**الحل**: تأكد من تثبيت JDK (وليس JRE)

### مشكلة 4: "Emulator won't start"
```bash
# حذف محاكي واسطنشاء جديد
# Tools → AVD Manager → حذف القديم → إنشاء جديد
```

### مشكلة 5: "Cannot resolve symbol 'Room'"
```bash
# قد تحتاج بعض الملفات للتحديث
./gradlew --refresh-dependencies
# ثم File → Invalidate Caches in Android Studio
```

---

## الإعدادات الموصى بها

### إعدادات Gradle لتحسين الأداء

عدّل `gradle.properties`:

```properties
# استخدام Gradle daemon
org.gradle.daemon=true

# استخدام parallel builds
org.gradle.parallel=true

# تخزين مؤقت
org.gradle.caching=true

# تخصيص الذاكرة
org.gradle.jvmargs=-Xmx4096m

# build features
android.enableJetifier=true
android.useAndroidX=true
```

### إعدادات Android Studio الموصى بها

1. **File → Settings → Appearance & Behavior → Appearance**
   - Theme: Material UI

2. **File → Settings → Editor → General**
   - Change font size with Ctrl+Mouse Wheel: ✓

3. **File → Settings → Build, Execution, Deployment → Gradle**
   - Gradle JVM: JAVA_HOME

---

## التحديث إلى إصدار جديد

```bash
# تحديث Gradle
./gradlew wrapper --gradle-version 8.x.x

# تحديث المكتبات
# عدّل app/build.gradle وحدّث أرقام الإصدارات
# ثم:
./gradlew dependencies --refresh-dependencies

# تنظيف بناء قديم
./gradlew clean
```

---

## الدعم الفني

### مصادر مفيدة:
- https://developer.android.com/studio
- https://developer.android.com/guide
- https://stackoverflow.com/questions/tagged/android-studio
- https://github.com/yourusername/AcademicServiceApp/issues

### تواصل مباشر:
- افتح Issue في GitHub
- أرسل بريداً إلكترونياً للدعم

---

**آخر تحديث**: سبتمبر 2026

---

## التحقق من النسخة

بعد التثبيت الكامل:

```bash
# تشغيل التطبيق
./gradlew installDebug

# فتح سجلات
./gradlew logcat
```

يجب أن ترى: `I/System.out: Application Started Successfully`
