# دليل المساهمة في المشروع

شكراً على اهتمامك بالمساهمة في تطبيق إدارة الخدمات الأكاديمية! نحن نرحب بجميع أشكال المساهمات.

## كيفية المساهمة

### 1. الإبلاغ عن الأخطاء (Issues)

إذا وجدت خطأ أو مشكلة:

1. **تحقق** من أن المشكلة لم تُرفع من قبل
2. **افتح Issue جديد** مع المعلومات التالية:
   - **العنوان**: وصف موجز للمشكلة
   - **الوصف**: شرح تفصيلي
   - **خطوات التكرار**: كيفية تكرار المشكلة
   - **النتيجة المتوقعة**: ماذا يجب أن يحدث
   - **النتيجة الفعلية**: ماذا حدث فعلاً
   - **البيئة**:
     - إصدار الأندرويد
     - اسم الجهاز
     - إصدار التطبيق

### 2. اقتراح الميزات (Feature Requests)

لاقتراح ميزة جديدة:

1. **افتح Issue** مع التصنيف `enhancement`
2. **صف الميزة**:
   - ما المشكلة التي تحل؟
   - كيف سيستفيد المستخدمون؟
   - أمثلة على الاستخدام

### 3. إرسال Pull Requests

#### الخطوات الأساسية:

```bash
# 1. Fork المشروع
# (اضغط Fork على GitHub)

# 2. استنساخ نسختك
git clone https://github.com/YOUR_USERNAME/AcademicServiceApp.git
cd AcademicServiceApp

# 3. إضافة upstream
git remote add upstream https://github.com/ORIGINAL_OWNER/AcademicServiceApp.git

# 4. إنشاء فرع جديد
git checkout -b feature/your-feature-name
# أو للإصلاحات:
git checkout -b fix/bug-description

# 5. قم بالتغييرات
# عدّل الملفات حسب الحاجة

# 6. اختبر التغييرات
# اختبر على محاكي وجهاز فعلي

# 7. Commit التغييرات
git add .
git commit -m "وصف واضح للتغييرات"

# 8. Push إلى Fork الخاص بك
git push origin feature/your-feature-name

# 9. افتح Pull Request
# من GitHub، اذهب إلى الفرع واضغط "Compare & pull request"
```

#### معايير Pull Request الجيد:

```markdown
## الوصف
شرح واضح لما تفعله هذه التغييرات

## نوع التغيير
- [ ] إصلاح خطأ (bug fix)
- [ ] ميزة جديدة (new feature)
- [ ] تحسين الأداء (performance improvement)
- [ ] تحسين التوثيق (documentation)

## التغييرات
- تغيير 1
- تغيير 2
- تغيير 3

## الاختبار
- [ ] اختبرت على Android 9
- [ ] اختبرت على Android 13
- [ ] اختبرت على جهاز فعلي
- [ ] جميع الاختبارات تمر ✓

## لائحة التحقق
- [ ] عدت مراجعة الكود الخاص بي
- [ ] أضفت التعليقات (comments)
- [ ] لم أضف/غيّر المكتبات الخارجية
- [ ] اتبعت معايير الكود
- [ ] حدّثت التوثيق
```

---

## معايير الكود (Coding Standards)

### تسمية المتغيرات والدوال

```kotlin
// ✅ صحيح
val studentName: String
fun getStudentById(id: Int)
class StudentAdapter

// ❌ خطأ
val sn: String
fun get_student_by_id(id: Int)
class student_adapter
```

### تنسيق الكود

```kotlin
// استخدم Kotlin style guide
// https://kotlinlang.org/docs/coding-conventions.html

// Indentation: 4 spaces
// Line length: 100 characters (soft limit), 120 (hard limit)

// ✅ صحيح
fun loadData() {
    val result = database.query()
    return result
}

// ❌ خطأ
fun loadData(){val result = database.query(); return result}
```

### التعليقات

```kotlin
// ✅ تعليقات واضحة
/**
 * يقوم بتحميل بيانات الطالب من قاعدة البيانات
 * @param studentId معرف الطالب الفريد
 * @return كيان الطالب أو null إذا لم يوجد
 */
fun loadStudent(studentId: Int): Student?

// اشرح "لماذا" وليس "ماذا"
// ❌ سيء: i++  // زيادة i
// ✅ جيد: i++  // انتقل إلى الطالب التالي
```

### معالجة الأخطاء

```kotlin
// ✅ صحيح: معالجة شاملة
try {
    val student = database.getStudent(id)
    showStudent(student)
} catch (e: DatabaseException) {
    Log.e("TAG", "خطأ في قاعدة البيانات", e)
    showError("فشل تحميل البيانات")
}

// ❌ خطأ: تجاهل الأخطاء
val student = database.getStudent(id)  // قد تفشل!
```

---

## البنية الموصى بها للملفات الجديدة

### لإضافة ميزة جديدة:

```
feature-name/
├── Model.kt              # كيانات البيانات
├── Repository.kt         # مستودع البيانات
├── ViewModel.kt          # نموذج العرض
├── Activity.kt           # النشاط (UI)
├── Adapter.kt            # محول RecyclerView
└── activity_layout.xml   # ملف التخطيط
```

### الملفات المطلوبة:

```kotlin
// داخل كل فئة جديدة
/**
 * [اسم الفئة]
 * 
 * الوصف: ماذا تفعل هذه الفئة
 * 
 * الاستخدام:
 *   val obj = ClassName()
 *   obj.method()
 * 
 * @author [اسمك]
 * @since 1.0.0
 */
class MyNewClass
```

---

## عملية المراجعة

### ما نبحث عنه:

✅ **يجب**:
- كود نظيف وقابل للقراءة
- بدون خطأ في الكود
- يتبع معايير المشروع
- مصحوب بـ tests
- توثيق واضح

❌ **لا يجب**:
- Commits كثيرة بدون معنى
- تغييرات غير ذات صلة
- كود معقد بدون حاجة
- حذف كود مهم
- تغيير structure المشروع

### التعليقات على Review:

```
// Comment Example:
// [BLOCKER] يجب تصحيح هذا قبل الدمج
// [MAJOR] مهم لكن يمكن إصلاحه بسرعة
// [MINOR] تحسين صغير
// [QUESTION] سؤال توضيحي
// [SUGGESTION] اقتراح تحسين
```

---

## التوثيق

### تحديث README:

إذا أضفت ميزة جديدة:

```markdown
### الميزة الجديدة

وصف قصير للميزة

```java
// مثال الاستخدام
code example
```
```

### تحديث USAGE.md:

اضف قسم جديد شارح للمستخدم:

```markdown
## الميزة الجديدة

### كيفية الاستخدام

1. خطوة 1
2. خطوة 2
3. خطوة 3

### مثال

صورة أو وصف بالصور
```

---

## الخطوات قبل الـ Pull Request النهائي

### قائمة التحقق:

```bash
# 1. تحديث من main
git fetch upstream
git rebase upstream/main

# 2. اختبار شامل
./gradlew test
./gradlew connectedAndroidTest

# 3. فحص الكود
./gradlew lint

# 4. بناء Release APK
./gradlew assembleRelease

# 5. التأكد من عدم وجود conflicts
git status

# 6. تنظيف الـ commits
git rebase -i upstream/main
# اضغط 'fixup' للـ commits الصغيرة

# 7. Push النسخة النهائية
git push origin feature/your-feature
```

---

## الاتصال والتواصل

- **Issues**: للمشاكل والاستفسارات
- **Discussions**: للأفكار والنقاشات العامة
- **Email**: للمواضيع الحساسة

---

## شكر خاص

شكراً لك على المساهمة! ساهماتك تحسن التطبيق للجميع 🙏

---

## أسئلة شائعة

**س: كم من الوقت قد يستغرق مراجعة PR؟**
ج: عادة 1-3 أيام حسب حجم التغيير

**س: هل يجب أن أغير إصدار الإصدار (version)?**
ج: لا، سيقوم الفريق بذلك عند الإصدار

**س: ماذا لو اختلفت مع مراجع؟**
ج: نناقش الموضوع بشكل بناء في التعليقات

**س: هل يمكن أن أعمل على عدة ميزات في نفس الوقت؟**
ج: نعم، لكن استخدم فرع منفصل لكل ميزة

---

**آخر تحديث**: سبتمبر 2026

شكراً لاهتمامك بالمشروع! 🚀
