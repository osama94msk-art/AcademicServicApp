# تطبيق إدارة الخدمات الأكاديمية والمالية

تطبيق اندرويد متكامل لإدارة ومتابعة الخدمات الطلابية الأكاديمية والمهام الدراسية والمالية.

## الميزات الرئيسية

### 1. إدارة بيانات الطلاب
- إضافة وتعديل وحذف بيانات الطلاب
- حفظ معلومات الطالب (الاسم، الرقم الجامعي، كلمة السر، التخصص، المستوى، رقم الجوال)
- إخفاء/إظهار كلمة السر مع تشفير التخزين
- تتبع الحالة المالية لكل طالب

### 2. إدارة المقررات والواجبات
- إضافة المقررات المسجلة لكل طالب
- ربط المقررات بقوائم الواجبات
- تتبع حالة الواجبات (قيد التنفيذ، مكتمل، متأخر)
- تحديد تواريخ واوقات التسليم
- خيار تحديد الموعد لاحقاً للواجبات غير المعروفة المواعيد

### 3. نظام التنبيهات والمذكرات
- عرض الواجبات العاجلة (الواجبات في آخر 24-48 ساعة)
- ترميز لوني حسب الأولوية:
  - 🔴 أحمر: حرج (آخر 24 ساعة)
  - 🟠 برتقالي: مرتفع (1-7 أيام)
  - 🟡 أصفر: متوسط (أسبوع+)
  - 🟢 أخضر: منخفض (مستقبلي)
- تنبيهات تذكير بمواعيد المحاضرات

### 4. التقويم الزمني
- عرض شهري وأسبوعي للواجبات
- عرض جميع مواعيد التسليم
- تصفية الواجبات حسب التاريخ
- تصفية الواجبات حسب الحالة

### 5. الإدارة المالية
- نظام مالي بسيط وفعال
- تتبع المبلغ الإجمالي والمدفوع والمتبقي
- سجل تفصيلي للدفعات والتعديلات
- حساب النسبة المئوية للسداد
- تصدير التقارير المالية (PDF/CSV)
- تقارير مفصلة وإجمالية

## المتطلبات الحد الأدنى
- أندرويد 5.0 (API 21) أو أحدث
- 50 MB مساحة تخزين
- الإنترنت (اختياري - التطبيق يعمل بدون إنترنت)

## البنية التقنية

### قاعدة البيانات
- Room Database للتخزين المحلي
- جداول متعددة مع علاقات قوية:
  - Students (الطلاب)
  - Courses (المقررات)
  - Assignments (الواجبات)
  - Lectures (المحاضرات)
  - FinancialRecords (السجلات المالية)

### العمارة
- MVVM Architecture
- LiveData و ViewModel
- Coroutines للعمليات غير المتزامنة
- Repository Pattern

### المكتبات المستخدمة
- **AndroidX**: Core libraries
- **Room**: Local database
- **Lifecycle**: MVVM components
- **Coroutines**: Async operations
- **Material Design**: UI components
- **Kizitonwose Calendar**: Calendar view
- **Lottie**: Animations

## التثبيت والتشغيل

### متطلبات التطوير
- Android Studio 2022.1 أو أحدث
- JDK 11 أو أحدث
- Gradle 8.0 أو أحدث
- SDK Android 34 أو أحدث

### خطوات البناء

1. **استنساخ المشروع**
```bash
git clone https://github.com/yourusername/AcademicServiceApp.git
cd AcademicServiceApp
```

2. **فتح المشروع**
```bash
# في Android Studio
File → Open → اختر مجلد المشروع
```

3. **تحميل المكتبات**
```bash
# Gradle سيقوم بهذا تلقائياً عند الفتح
./gradlew build
```

4. **تشغيل على محاكي أو جهاز**
```bash
./gradlew installDebug
# أو من خلال Android Studio (Run → Run 'app')
```

### بناء ملف APK

#### APK للتطوير (Debug)
```bash
./gradlew assembleDebug
# الملف سيكون في: app/build/outputs/apk/debug/app-debug.apk
```

#### APK للإنتاج (Release)
```bash
# يجب توفير ملف Keystore
./gradlew assembleRelease -Pandroid.injected.signing.store.file=your.keystore -Pandroid.injected.signing.store.password=password -Pandroid.injected.signing.key.alias=alias -Pandroid.injected.signing.key.password=password
# الملف سيكون في: app/build/outputs/apk/release/app-release.apk
```

## هيكل المشروع

```
AcademicServiceApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/academicservice/
│   │   │   ├── data/
│   │   │   │   ├── database/    # قاعدة البيانات
│   │   │   │   ├── dao/         # واجهات الوصول
│   │   │   │   ├── models/      # كيانات البيانات
│   │   │   │   └── repository/  # مستودعات البيانات
│   │   │   ├── ui/
│   │   │   │   ├── activities/  # الأنشطة الرئيسية
│   │   │   │   ├── adapter/     # محولات RecyclerView
│   │   │   │   └── viewmodel/   # نماذج العرض
│   │   │   ├── notification/    # نظام التنبيهات
│   │   │   └── utils/           # الأدوات المساعدة
│   │   ├── res/
│   │   │   ├── layout/          # ملفات التخطيط
│   │   │   ├── drawable/        # الصور والأيقونات
│   │   │   ├── values/          # الألوان والنصوص
│   │   │   └── menu/            # قوائم التطبيق
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

## الاستخدام

### إضافة طالب جديد
1. انقر على زر "+" في الشاشة الرئيسية
2. أدخل جميع البيانات المطلوبة
3. انقر "حفظ"

### إضافة مقرر
1. افتح صفحة الطالب
2. انقر على زر "+" في قسم المقررات
3. أدخل معلومات المقرر
4. انقر "حفظ"

### إضافة واجب
1. انتقل إلى المقرر
2. انقر على "+" لإضافة واجب
3. أدخل التفاصيل والموعد النهائي
4. انقر "حفظ"

### عرض التقارير المالية
1. من صفحة الطالب، انقر "التقرير المالي"
2. اعرض ملخص الحالة المالية
3. استعرض السجلات والدفعات
4. صدّر التقرير إذا لزم الأمر

## قاعدة البيانات

### جدول Students
```
id (Int) - المعرف الأساسي
name (String) - الاسم
universityId (String) - الرقم الجامعي
password (String) - كلمة السر
specialty (String) - التخصص
level (String) - المستوى
phoneNumber (String) - رقم الجوال
totalAmount (Double) - المبلغ الإجمالي
paidAmount (Double) - المدفوع
remainingAmount (Double) - المتبقي
createdDate (Long) - تاريخ الإضافة
notes (String) - ملاحظات
```

### جدول Courses
```
id (Int) - المعرف الأساسي
studentId (Int) - معرف الطالب (مفتاح أجنبي)
courseName (String) - اسم المقرر
courseCode (String) - رمز المقرر
instructor (String) - المحاضر
creditHours (Int) - الساعات المعتمدة
semester (String) - الفصل الدراسي
scheduleTime (String) - وقت الجدول
room (String) - القاعة
notes (String) - ملاحظات
```

### جدول Assignments
```
id (Int) - المعرف الأساسي
courseId (Int) - معرف المقرر (مفتاح أجنبي)
assignmentName (String) - اسم الواجب
description (String) - الوصف
dueDate (Long) - تاريخ الاستحقاق
dueTime (String) - وقت التسليم
status (Enum) - الحالة (قيد التنفيذ، مكتمل، متأخر)
submissionDate (Long) - تاريخ التسليم الفعلي
score (Int) - الدرجة
maxScore (Int) - أقصى درجة
notes (String) - ملاحظات
isUndefinedDate (Boolean) - هل الموعد غير محدد
```

### جدول FinancialRecords
```
id (Int) - المعرف الأساسي
studentId (Int) - معرف الطالب (مفتاح أجنبي)
description (String) - الوصف
amount (Double) - المبلغ
transactionType (Enum) - نوع العملية
transactionDate (Long) - تاريخ العملية
notes (String) - ملاحظات
```

## التصميم واللغة

- **اللغة**: العربية (دعم كامل RTL)
- **التصميم**: Material Design 3
- **الألوان**:
  - الأساسي: أزرق (#1976D2)
  - الثانوي: أصفر ذهبي (#FFC107)
  - الخطأ: أحمر (#F44336)
  - النجاح: أخضر (#4CAF50)
  - التحذير: برتقالي (#FF9800)

## المساهمة

نرحب بالمساهمات! يرجى:

1. Fork المشروع
2. إنشاء فرع للميزة (`git checkout -b feature/AmazingFeature`)
3. Commit التغييرات (`git commit -m 'Add some AmazingFeature'`)
4. Push إلى الفرع (`git push origin feature/AmazingFeature`)
5. فتح Pull Request

## الترخيص

هذا المشروع مرخص تحت MIT License - انظر ملف LICENSE للتفاصيل.

## الدعم والمساعدة

للمزيد من المعلومات أو الإبلاغ عن مشاكل:
- افتح Issue في GitHub
- تواصل عبر البريد الإلكتروني

## المؤلف

تم تطوير هذا التطبيق بواسطة فريق التطوير المتخصص في الحلول الأكاديمية.

## الإصدارات والتحديثات

### الإصدار 1.0.0 (الحالي)
- إدارة الطلاب والمقررات والواجبات
- نظام التنبيهات والمذكرات
- التقويم الزمني
- الإدارة المالية والتقارير
- دعم العربية الكامل

## خريطة الطريق المستقبلية

- [ ] واجهة ويب للإدارة
- [ ] المزامنة السحابية
- [ ] تطبيق iOS
- [ ] نظام تقييم الطلاب
- [ ] تصدير البيانات
- [ ] تقارير إحصائية متقدمة
- [ ] دعم لغات إضافية
- [ ] وضع مظلم محسّن

---

**آخر تحديث**: سبتمبر 2026
