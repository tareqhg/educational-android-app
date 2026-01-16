package com.example.tareq_second;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup rgLanguage;
    Spinner spTextSize;
    SharedPreferences prefs;
    boolean isInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rgLanguage = findViewById(R.id.rgLanguage);
        spTextSize = findViewById(R.id.spTextSize);

        prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);

        loadSettings();

        rgLanguage.setOnCheckedChangeListener((group, checkedId) -> {
            if (!isInit) {
                prefs.edit().putString("lang",
                        checkedId == R.id.rbArabic ? "ar" : "en").apply();
            }
        });

        spTextSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (isInit) return;

                String size;
                if (position == 0) {
                    size = "small";
                } else if (position == 1) {
                    size = "medium";
                } else {
                    size = "large";
                }

                prefs.edit().putString("size", size).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // لا شيء
            }
        });


        isInit = false;
    }

    private void loadSettings() {
        String lang = prefs.getString("lang", "ar");
        String size = prefs.getString("size", "medium");

        rgLanguage.check(lang.equals("ar") ? R.id.rbArabic : R.id.rbEnglish);
        spTextSize.setSelection(size.equals("small") ? 0 : size.equals("medium") ? 1 : 2);
    }
}