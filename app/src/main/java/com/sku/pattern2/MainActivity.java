package com.sku.pattern2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.andrognito.patternlockview.utils.PatternLockUtils;


public class MainActivity extends AppCompatActivity {

    Button btn_Create, btn_Change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Create = (Button) findViewById(R.id.btn_create_pattern);
        btn_Change = (Button) findViewById(R.id.btn_change_pattern);

        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences preferences = getSharedPreferences("PREFS", 0); // 저장된 값을 불러오기 위해 "PREFS"라는 네임파일을 찾음
                        String password = preferences.getString("password", "0"); // password라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 "0"을 반환
                        if(password.equals("0")) { // password라는 key에 값이 없을 경우 (패턴이 없을 경우)
                            Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplication(), "패턴이 이미 생성되어 있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 500);
            }
        });

        btn_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                        String password = preferences.getString("password", "0");
                        if(password.equals("0")) {
                            Toast.makeText(getApplication(), "패턴을 생성해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), CheckPasswordActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 500);
            }
        });
    }
}
