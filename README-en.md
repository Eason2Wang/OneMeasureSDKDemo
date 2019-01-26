Android OneMeasureSDK Documentation ( [https://github.com/Eason2Wang/OneMeasureSDKDemo/blob/master/README.md](中文文档))

-----
 __Download Demo: __ [https://github.com/Eason2Wang/OneMeasureSDKDemo](https://github.com/Eason2Wang/OneMeasureSDKDemo)
## 1.1 Setup
#### 1.1.1  Gradle Configuration
Add jitpack remote dependency to your build.gradle in your project root directory：

``` 
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
``` 
Add the following configurations to your build.gradle in your module：

- Add java8 feature support：

``` 
android {
    ...
    compileOptions {
        targetCompatibility JavaVersion.VERSION_1_8
        sourceCompatibility JavaVersion.VERSION_1_8
    }
    ...
}
``` 
- Add OneMeasureSDK dependency：

``` 
dependencies {
    ...
    implementation "com.tozmart:tozmartSDK-s3:1.2.1"
    ...
}
``` 
#### 1.1.2 Configure Your Application Class
If you have already configured your own Application class in your project，make this class implements com.jess.arms.base.App and add the following codes：

``` 
public class MyApplication extends Application implements App {
    ...
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ...
        ToziSDK.getInstance().attachBaseContext(base);
        ...
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ...
        ToziSDK.getInstance().onCreate(this);
        ...
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ...
        ToziSDK.getInstance().onTerminate(this);
        ...
    }

    @Override
    public AppComponent getAppComponent() {
        return ((App) ToziSDK.getInstance().getmAppDelegate()).getAppComponent();
    }
    ...
}
``` 
If your project dose not contain an Application class，you need to add one and configure it as above. At the same time, pay attention to adding a reference to this class in the AndroidManifest file：

``` 
<application
    android:name=".MyApplication"
    ... />
</application>
``` 
## 1.2 API Instruction
#### 1.2.1 How to Call API
Add the following codes where you need to call SDK API:

``` 
new ToziSDK.Builder()
        .setUserId("user id")
        .setCorpId("corporation id")
        .setName("wys")
        .setGender(Gender.MALE)
        .setHeight(180.f)
        .setWeight(70.f)
        .setLanguage(Language.CHINESE)
        .setUnit(Unit.METRIC)
        .setBlurFace(false)
        .setCameraMode(CameraMode.SELFIE)
        .start(MainActivity.this);
``` 
#### 1.2.2 Parameters Instruction

| Parameters | Type | Required | API | Description |
| ------ | ------ | ------ | ------ | ------ |
| corpId | String | Yes | setCorpId(corpId)|Corporation id，an unique id distributed by TOZI|
| userId | String | Yes | setUserId(userId)|User's id，defined by corporation|
|name|String|Yes|setName(name)|User's name|
|gender|int|Yes|setGender(gender)|User's gender：Gender.MALE、Gender.FEMALE|
|height|float|Yes|setHeight(height)|User's height(cm)|
|weight|float|Yes|setWeight(weight)|User's weight(kg)|
|language|int|No|setLanguage(language)|language used by measurements showing（Be noticed that this parameter will not change the language of your APP）：Language.ENGLISH（Default）、Language.CHINESE（Simple Chinese）、Language.TRADITION_CHINESE（Traditional Chinese）、Language.JAPANESE|
|unit|int|No|setUnit(unit)|language used by measurements showing：Unit.METRIC（cm/kg, Default）、IUnit.IMPERIAL(inch/lbs)|
|isBlurFace|Boolean|No|setBlurFace(isBlurFace)|Whether to blur the face of the photos taken，default is true|
|cameraMode|int|No|setCameraMode(cameraMode)|Set camera mode：CameraMode.SNAPSHOT（take photos by others，default）、CameraMode.SELFIE（take photos by yourself）|

 __Download Demo: __ [https://github.com/Eason2Wang/OneMeasureSDKDemo](https://github.com/Eason2Wang/OneMeasureSDKDemo)
