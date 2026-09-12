# تعليمات رفع المشروع على GitHub

دليل شامل لرفع تطبيق إدارة الخدمات الأكاديمية على GitHub وتحضيره للاستخدام.

---

## 1️⃣ إنشاء حساب GitHub

### إذا لم تكن قد أنشأت حساب بعد:

1. اذهب إلى https://github.com/signup
2. أدخل بريدك الإلكتروني
3. اختر كلمة سر قوية
4. أكمل التحقق من البريد الإلكتروني
5. أكمل الإعداد الأساسي

---

## 2️⃣ إعداد Git محلياً

### تثبيت Git

#### Windows:
- اذهب إلى https://git-scm.com/download/win
- حمّل وشغّل المثبت
- اتبع الخطوات الافتراضية

#### macOS:
```bash
brew install git
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt-get update
sudo apt-get install git
```

### إعداد بيانات Git

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# تحقق من الإعدادات
git config --global --list
```

---

## 3️⃣ إنشاء مستودع على GitHub

### الخطوات:

1. **في GitHub**:
   - اضغط على `+` في الأعلى يساراً
   - اختر `New repository`
   - أدخل اسم المستودع: `AcademicServiceApp`
   - اكتب وصف: "تطبيق إدارة الخدمات الأكاديمية والمالية"
   - اختر `Public` (عام)
   - ✅ تأكد من عدم تحديد "Initialize with README" (لأننا لدينا واحد بالفعل)
   - اضغط `Create repository`

2. **ستجد صفحة الإعدادات الأولية**:
   - نسخ رابط المستودع (HTTPS أو SSH)

---

## 4️⃣ رفع المشروع

### الطريقة الأولى: من Terminal (الموصى بها)

```bash
# 1. انتقل إلى مجلد المشروع
cd /path/to/AcademicServiceApp

# 2. تهيئة Git محلياً
git init

# 3. أضف جميع الملفات
git add .

# 4. أول commit
git commit -m "Initial commit: تطبيق إدارة الخدمات الأكاديمية الكامل"

# 5. أضف المستودع البعيد
git remote add origin https://github.com/YOUR_USERNAME/AcademicServiceApp.git

# 6. أعد تسمية الفرع (إذا لزم الأمر)
git branch -M main

# 7. ارفع الكود
git push -u origin main

# الآن أدخل بيانات حسابك على GitHub
```

### الطريقة الثانية: من GitHub Desktop

1. حمّل GitHub Desktop من https://desktop.github.com
2. سجّل الدخول بحسابك
3. اختر `File → Clone Repository`
4. أدخل رابط المستودع
5. اختر مكان التخزين المحلي
6. اضغط `Clone`

---

## 5️⃣ اختبار الرفع

### تحقق من أن كل شيء تم رفعه:

```bash
# عرض حالة Git
git status

# سيجب أن تشاهد: "nothing to commit, working tree clean"

# تحقق من الملفات المرفوعة
git log

# ستشاهد سجل الـ commits
```

### في GitHub:

1. اذهب إلى صفحة المستودع
2. تأكد من ظهور جميع الملفات
3. تحقق من README.md معروض بشكل صحيح

---

## 6️⃣ إعدادات GitHub المهمة

### تفعيل Issues

1. اذهب إلى **Settings** → **Features**
2. تأكد من تفعيل **Issues** ✓
3. تأكد من تفعيل **Discussions** ✓

### إضافة وصف المشروع

1. اذهب إلى الصفحة الرئيسية للمستودع
2. اضغط على **Edit** (أيقونة القلم)
3. أضف الوصف والرابط والمواضيع

### إضافة ملفات GitHub الخاصة

```bash
# في المجلد الرئيسي للمستودع، أنشئ:

mkdir -p .github

# أضف ملف description
echo "# AcademicServiceApp" > .github/ISSUE_TEMPLATE/bug_report.md
```

---

## 7️⃣ رفع التحديثات اللاحقة

### بعد إجراء تغييرات محلية:

```bash
# 1. عرض التغييرات
git status

# 2. أضف الملفات المعدلة
git add .

# أو لملفات محددة:
git add filename.kt

# 3. أنشئ commit
git commit -m "وصف التغييرات"

# 4. ارفع التحديث
git push origin main
```

### مثال على رسائل Commit جيدة:

```
✅ الصيغة الجيدة:
- "إضافة ميزة البحث في الطلاب"
- "إصلاح خطأ في حساب النسبة المئوية"
- "تحديث التوثيق"

❌ الصيغة السيئة:
- "fix bug"
- "update"
- "اختبار"
```

---

## 8️⃣ الفروع (Branches)

### إنشاء فرع جديد للميزات

```bash
# أنشئ فرع جديد
git checkout -b feature/new-feature

# أجري التغييرات
# ... عدّل الملفات ...

# أضف وأرفع
git add .
git commit -m "إضافة الميزة الجديدة"
git push origin feature/new-feature

# ثم افتح Pull Request على GitHub
```

### إرشادات الفروع

```
main ...................... الإصدار المستقر النهائي
├── develop ............... النسخة قيد التطوير
│   ├── feature/auth
│   ├── feature/reports
│   └── bugfix/calendar
└── release/v1.1 ......... إصدار جديد قيد الإعداد
```

---

## 9️⃣ إدارة الإصدارات (Releases)

### إنشاء إصدار جديد

1. اذهب إلى **Releases** في صفحة المستودع
2. اضغط **Create a new release**
3. أدخل رقم الإصدار: `v1.0.0`
4. أضف العنوان والوصف
5. أرفع ملف APK
6. اضغط **Publish release**

### ملف APK:

```bash
# بناء APK release
./gradlew assembleRelease

# ستجد الملف في:
# app/build/outputs/apk/release/app-release.apk

# أرفعه في صفحة Release
```

---

## 🔟 الإحصائيات والشارات

### إضافة شارة في README

```markdown
[![GitHub License](https://img.shields.io/github/license/yourusername/AcademicServiceApp)](LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/yourusername/AcademicServiceApp)](../../stargazers)
[![Android API](https://img.shields.io/badge/API-21%2B-brightgreen)](https://android-developers.googleblog.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8.0-purple)](https://kotlinlang.org)
```

### متابعة الإحصائيات

في **Insights** يمكنك رؤية:
- عدد النجوم
- عدد المتابعين
- عدد الـ Issues
- عدد الـ Pull Requests

---

## 1️⃣1️⃣ التعاون مع الآخرين

### إضافة متعاونين

1. اذهب إلى **Settings** → **Collaborators**
2. اضغط **Add people**
3. أدخل اسم المستخدم
4. اختر الصلاحيات
5. أرسل الدعوة

### القيود:

- **Pull access**: قراءة فقط
- **Push access**: قراءة وكتابة
- **Admin access**: تحكم كامل

---

## 1️⃣2️⃣ حماية المستودع

### تفعيل Branch Protection

1. اذهب إلى **Settings** → **Branches**
2. اضغط **Add rule** تحت Branch protection rules
3. اختر الفرع: `main`
4. فعّل:
   - ✓ Require pull request reviews
   - ✓ Require code owners review
   - ✓ Require status checks to pass

---

## 1️⃣3️⃣ الأمان

### Secrets و API Keys

**لا تنسَ أبداً:**
- ❌ لا تضع كلمات سر في الكود
- ❌ لا تضع مفاتيح API
- ❌ لا تضع بيانات خاصة
- ❌ لا تضع ملفات Keystore

### ملفات .gitignore

```gitignore
# لا تنسَ هذه:
*.keystore
*.jks
.DS_Store
.gradle/
build/
.idea/
*.iml
local.properties
```

---

## 1️⃣4️⃣ النشر على Google Play

### المتطلبات

1. ✅ مستودع GitHub جاهز
2. ✅ APK Release موقّع
3. ✅ حساب Google Play Developer ($25)
4. ✅ صور وأيقونات عالية الجودة

### الخطوات

1. أنشئ حساب على https://play.google.com/console
2. اضغط **Create app**
3. أدخل اسم التطبيق
4. أكمل الإجراءات الأساسية
5. ارفع APK Release
6. أكمل البيانات الوصفية
7. اختبر على الأجهزة
8. أرسل للمراجعة

---

## 1️⃣5️⃣ الصيانة المستمرة

### بعد النشر

- 📅 **أسبوعياً**: افحص Issues
- 🔄 **شهرياً**: حدّث المكتبات
- 🐛 **فوراً**: صحح الأخطاء
- 📝 **دورياً**: حدّث التوثيق
- 🧪 **دائماً**: اختبر التغييرات

### ملف CHANGELOG

```markdown
# Changelog

## [1.0.0] - 2026-09-01

### Added
- الإصدار الأول من التطبيق
- إدارة الطلاب
- إدارة المقررات
- نظام التنبيهات
- الإدارة المالية

### Fixed
- إصلاح أخطاء الواجهة

### Changed
- تحسينات الأداء
```

---

## مشاكل شائعة وحلولها

### مشكلة 1: "Repository not found"
```bash
# تحقق من الرابط
git remote -v

# وغيّره إذا لزم:
git remote set-url origin https://github.com/YOUR_USERNAME/AcademicServiceApp.git
```

### مشكلة 2: "Permission denied"
```bash
# قد تحتاج إلى إعادة المصادقة
git credential reject https://github.com

# أو استخدم SSH key
ssh-keygen -t ed25519 -C "your@email.com"
```

### مشكلة 3: Large files
```bash
# Git لا يقبل ملفات > 100 MB
# استخدم Git LFS:
git lfs install
git lfs track "*.apk"
git add .gitattributes
```

---

## أوامر Git السريعة

```bash
# عرض حالة المستودع
git status

# عرض السجل
git log --oneline

# عرض الفروع
git branch -a

# حذف فرع محلي
git branch -d feature/name

# حذف فرع بعيد
git push origin --delete feature/name

# إعادة آخر commit
git reset --soft HEAD~1

# حذف آخر commit
git reset --hard HEAD~1
```

---

## الموارد المفيدة

- [GitHub Help](https://help.github.com)
- [Git Documentation](https://git-scm.com/doc)
- [GitHub Flow](https://guides.github.com/introduction/flow)
- [Semantic Versioning](https://semver.org)
- [Keep a Changelog](https://keepachangelog.com)

---

## ✅ قائمة التحقق النهائية

- [ ] حساب GitHub نشط
- [ ] Git مثبت ومهيأ محلياً
- [ ] مستودع أنشئ على GitHub
- [ ] المشروع رُفع بنجاح
- [ ] README يظهر بشكل صحيح
- [ ] جميع الملفات موجودة
- [ ] لا توجد حساسة (secrets) في الكود
- [ ] .gitignore مناسب
- [ ] Issues مفعّلة
- [ ] Releases معدّة
- [ ] متعاونون أضيفوا (اختياري)
- [ ] Branch protection مفعّل (اختياري)

---

## 🎉 تم!

تهانينا! مشروعك الآن على GitHub وجاهز للعالم!

```bash
# تحقق من أن كل شيء يعمل:
git log --oneline | head -5
git remote -v
```

---

**آخر تحديث**: سبتمبر 2026

📧 **للمزيد من المساعدة**: افتح Issue في المستودع! 🚀
