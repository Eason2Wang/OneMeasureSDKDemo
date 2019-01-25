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
                        .setBlurFace(true)
                        .setCorpId("o4x04hy3zljghf7oa4")
                        .setUserId("12345")
                        .setGender(Gender.FEMALE)
                        .setHeight(180)
                        .setWeight(70)
                        .setLanguage(Language.ENGLISH)
                        .setName("wys")
                        .setUnit(Unit.IMPERIAL)
                        .setCameraMode(CameraMode.SNAPSHOT)
                        .start(MainActivity.this)
        );
    }
}
