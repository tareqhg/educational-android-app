package com.example.tareq_second;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // عناوين ووصف الكورسات
    private TextView tvCourse1Title, tvCourse1Desc;
    private TextView tvCourse2Title, tvCourse2Desc;
    private TextView tvCourse3Title, tvCourse3Desc;

    private Button btnSettings;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر
        btnSettings = findViewById(R.id.btnSettings);

        tvCourse1Title = findViewById(R.id.tvCourse1Title);
        tvCourse1Desc  = findViewById(R.id.tvCourse1Desc);

        tvCourse2Title = findViewById(R.id.tvCourse2Title);
        tvCourse2Desc  = findViewById(R.id.tvCourse2Desc);

        tvCourse3Title = findViewById(R.id.tvCourse3Title);
        tvCourse3Desc  = findViewById(R.id.tvCourse3Desc);

        // SharedPreferences
        prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // تطبيق الإعدادات عند فتح التطبيق
        applyPreferences();

        // زر الإعدادات
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // إعادة تطبيق الإعدادات بعد الرجوع من شاشة الإعدادات
        applyPreferences();
    }

    private void applyPreferences() {

        String lang = prefs.getString("lang", "ar");
        String size = prefs.getString("size", "medium");

        // تحديد حجم الخط
        float textSize;
        if (size.equals("small")) {
            textSize = 14f;
        } else if (size.equals("large")) {
            textSize = 20f;
        } else {
            textSize = 16f; // medium
        }

        // كل TextView في مصفوفة واحدة
        TextView[] allTextViews = {
                tvCourse1Title, tvCourse1Desc,
                tvCourse2Title, tvCourse2Desc,
                tvCourse3Title, tvCourse3Desc
        };

        // تطبيق حجم الخط واتجاه النص
        for (TextView tv : allTextViews) {
            tv.setTextSize(textSize);
            tv.setTextDirection(
                    lang.equals("ar")
                            ? View.TEXT_DIRECTION_RTL
                            : View.TEXT_DIRECTION_LTR
            );
        }

        // تغيير المحتوى حسب اللغة
        if (lang.equals("en")) {

            tvCourse1Title.setText("Programming Basics");
            tvCourse1Desc.setText("Learn the fundamentals of programming step by step.");

            tvCourse2Title.setText("Android Development");
            tvCourse2Desc.setText("Build Android applications using Java.");

            tvCourse3Title.setText("Algorithms");
            tvCourse3Desc.setText("Understand logical thinking and problem solving.");

        } else {

            tvCourse1Title.setText("أساسيات البرمجة");
            tvCourse1Desc.setText("تعلم المفاهيم الأساسية في البرمجة خطوة بخطوة.");

            tvCourse2Title.setText("تطوير تطبيقات أندرويد");
            tvCourse2Desc.setText("تعلم بناء تطبيقات أندرويد باستخدام Java.");

            tvCourse3Title.setText("الخوارزميات");
            tvCourse3Desc.setText("فهم التفكير المنطقي وحل المشكلات.");
        }
    }
}
