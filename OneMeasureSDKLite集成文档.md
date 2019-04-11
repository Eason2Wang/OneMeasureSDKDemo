## OneMeasureSDKLite集成文档

*新版SDK更加轻量化，集成方式更加简便，并且高度可定制化*

[Demo Github传送门](https://github.com/Eason2Wang/OneMeasureSDKDemo)

 **1. 集成准备**
 
**1.1 gradle配置**

在需要集成 OneMeasureSDKLite 的 module 的 build.gradle文件中添加如下配置：
 - 添加 OneMeasureSDKLite 依赖：

```
dependencies {
    ...
    implementation 'com.tozmart:tozmartSDK-s4:1.0.1'
    ...
}
```
 **2. SDK使用说明**
 
 **2.1 初始化SDK**

在调用SDK的各项接口之前，必须先初始化SDK。

**2.1.1 初始化**

 在需要使用OneMeasureSDKLite的地方添加如下代码即可调用sdk：
```
new OneMeasureSDKLite.Builder()
                .withActivity(this)
                .setCorpId("你的企业id")
                .setUserId("你的用户id")
                .setName("wys")
                .setGender(Gender.MALE)
                .setHeight(180)
                .setWeight(75)
                .setLanguage(Language.ENGLISH)
                .build();
```
**2.1.2 参数说明**

| 参数 | 类型 | 是否必填 | 接口 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| activity | android.app.Activity | 是 | withActivity(activity)|当前活动的Activity，用来获取上下文|
| corpId | String | 是 | setCorpId(corpId)|企业账户的id，由图郅公司分配给每个企业的唯一id|
| userId | String | 是 | setUserId(userId)|企业自己分配给其用户的id，由企业自行定义|
|name|String|是|setName(name)|用户的名字|
|gender|int|是|setGender(gender)|用户的性别：Gender.MALE、Gender.FEMALE|
|height|float|是|setHeight(height)|用户的身高，单位是cm|
|weight|float|是|setWeight(weight)|用户的体重，单位是kg|
|language|int|否|setLanguage(language)|尺寸显示的语言（注意此参数并不会修改app的默认语言，只会修改尺寸展示相关的语言）：Language.ENGLISH（英语，默认）、Language.CHINESE（简体中文）、Language.TRADITION_CHINESE（繁体中文）、Language.JAPANESE（日语）|

**2.2 CameraView的使用**

CameraView是SDK提供给用户的相机控件，该控件集成了相机的拍照和设备姿态检测等功能，减少用户的二次开发时间。

**2.2.1 使用方法**

在Layout的XML文件中添加如下代码：

```
<com.tozmart.tozisdk.view.CameraView
    android:id="@+id/camera_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</com.tozmart.tozisdk.view.CameraView>
```
然后，在相关的Activity中初始化CameraView并添加如下代码，包括注册相机和传感器：

```
CameraView cameraView;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_front_camera);
    
    cameraView = findViewById(R.id.camera_view);
    ...
}

@Override
public void onStart() {
    super.onStart();
    try {
        cameraView.onStart();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Override
public void onResume() {
    super.onResume();
    try {
        cameraView.onResume();
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 注册传感器
    cameraView.registerSensor();
}

@Override
public void onPause() {
    try {
        cameraView.onPause();
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 取消传感器
    cameraView.unregisterSensor();
    super.onPause();
}

@Override
public void onStop() {
    try {
        cameraView.onStop();
    } catch (Exception e) {
        e.printStackTrace();
    }
    super.onStop();
}

@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    //申请camera权限; Apply for camera permission
    cameraView.onRequestPermissionsResult(requestCode, permissions, grantResults);
}
```

**2.2.2 CameraView相关接口**

 

 - **captureImage**

拍摄照片及回调接口：

```
cameraView.captureImage(new CameraView.ImageCallback() {
    @Override
    public void onImage(CameraView view, byte[] jpeg) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
        ...
    }
});
```
 - **setFacing**

设置使用前置摄像头或者后置摄像头接口：
 

```
// CameraKit.FACING_FRONT or CameraKit.FACING_BACK
cameraView.setFacing(CameraView.FACING_BACK);
```
 - **getFacing**

获取当前摄像头的状态接口：

```
// 返回CameraKit.FACING_FRONT or CameraKit.FACING_BACK
cameraView.getFacing();
```
 - **toggleFacing**

切换前后置摄像头的接口，如果当前摄像头是后置，则切换为前置，反之亦然：

```
toggle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cameraView.toggleFacing();
    }
});
```
 - **openGalleryFromActivity / openGalleryFromFragment**

从当前的Activity或者Fragment打开设备图库，选取的图库图片需要从onActivityResult获取：

```
public static final int REQUEST_GALLERY = 9162;
...
cameraView.openGalleryFromActivity(FrontCameraActivity.this, REQUEST_GALLERY);
...
@Override
public void onActivityResult(int requestCode, int resultCode, Intent result) {
    super.onActivityResult(requestCode, resultCode, result);
    if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
        if (result.getData() != null) {
            try {
                Bitmap imageResult = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```
 - **lackRequiredSensors**

判断该设备是否缺少必要的检测设备姿态的传感器，在获取传感器相关数据之前必须先检测传感器是否合格：

```
if (cameraView.lackRequiredSensors()) {
    Toast.makeText(this, "lack sensors", Toast.LENGTH_SHORT).show();
}  else {
    ...
}
```
 - **setOnSensorListener**

获取传感器的相关数据：

```
cameraView.setOnSensorListener(new CameraView.OnSensorListener() {
    @Override
    public void onSensorOk() {
    	// 手机姿态正确，可进行拍照；device pose is ok, you can take piture
        Log.i("sensor:", "ok");
    }

    @Override
    public void onSensorError() {
        // 手机姿态错误，不可进行拍照；device pose is not ok, you can not take piture
        Log.i("sensor:", "error");
    }

    @Override
    public void onSensorAngle(float sensorFB, float sensorLR) {
        // sensorFB 获取设备前后倾斜的角度; get the front-back angle of your device; 设备朝前倾斜是负数度数，向后是正数; front negative, back positive
        // sensorLR 获取设备左右倾斜角度; get the left-right angle of your device; 左负右正；left negative, right positive
        ...
   }
});
```

