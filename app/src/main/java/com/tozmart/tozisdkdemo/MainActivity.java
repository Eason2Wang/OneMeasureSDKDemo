package com.tozmart.tozisdkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tozmart.tozisdk.app.ToziSDK;
import com.tozmart.tozisdk.constant.Gender;
import com.tozmart.tozisdk.constant.Language;
import com.tozmart.tozisdk.constant.Unit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ToziSDK.Builder()
                        .setBlurFace(false)
                        .setCorpId("o4x04hy3zljghf7oa4")
                        .setUserId("12345")
                        .setGender(Gender.MALE)
                        .setHeight(180)
                        .setWeight(70)
                        .setLanguage(Language.CHINESE)
                        .setName("wys")
                        .setUnit(Unit.METRIC)
                        .start(MainActivity.this);
            }
        });
    }
}
