package com.tozmart.tozisdkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tozmart.tozisdk.app.ToziSDK;
import com.tozmart.tozisdk.constant.CameraMode;
import com.tozmart.tozisdk.constant.Gender;
import com.tozmart.tozisdk.constant.Language;
import com.tozmart.tozisdk.constant.Unit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start).setOnClickListener(v ->
                new ToziSDK.Builder()
                        .setCorpId("你的用户id")
                        .setUserId("你的企业id")
                        .setName("wys")
                        .setGender(Gender.MALE)
                        .setHeight(180.f)
                        .setWeight(70.f)
                        .setLanguage(Language.CHINESE)
                        .setUnit(Unit.METRIC)
                        .setCameraMode(CameraMode.SNAPSHOT)
                        .setBlurFace(false)
                        .start(MainActivity.this)
        );
    }
}
