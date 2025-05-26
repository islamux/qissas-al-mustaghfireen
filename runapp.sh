#!/bin/bash

# تحديد مسار build.gradle (في Flutter: android/app/build.gradle)
GRADLE_FILE=$(find . -type f -name "build.gradle" | grep "app/build.gradle" | head -n 1)

if [ ! -f "$GRADLE_FILE" ]; then
  echo "❌ لم يتم العثور على ملف build.gradle المناسب."
  exit 1
fi

# استخراج applicationId من build.gradle
PACKAGE_NAME=$(grep applicationId "$GRADLE_FILE" | head -n 1 | awk '{print $2}' | tr -d '"')

if [ -z "$PACKAGE_NAME" ]; then
  echo "❌ لم يتم العثور على applicationId في $GRADLE_FILE"
  exit 1
fi

echo "✅ تشغيل التطبيق ($PACKAGE_NAME) على الجهاز..."

# تشغيل التطبيق باستخدام adb
adb shell monkey -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1
