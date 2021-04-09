package com.sku.pattern2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import java.util.List;

public class CheckPasswordActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;
    String password;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        password = preferences.getString("password", "0");

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if(password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                    Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if(count<5) {
                        Toast.makeText(CheckPasswordActivity.this, count + "회 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                        mPatternLockView.clearPattern();
                        count++;
                    } else if(count==5) {
                        Toast.makeText(CheckPasswordActivity.this, "5회 이상 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }
}
