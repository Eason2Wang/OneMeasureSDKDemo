Android OneMeasureSDK 集成文档 ([English Documentation](https://github.com/Eason2Wang/OneMeasureSDKDemo/blob/master/README-en.md))

-----
新版SDK全面改版：
- 算法全新升级，正面展示全身轮廓，轮廓预测更加准确；
- UI全新改版，用户体验更加友好
- SDK体积缩小至不足300K，集成和调用更加方便

 __Demo下载地址：__ [https://github.com/Eason2Wang/OneMeasureSDKDemo](https://github.com/Eason2Wang/OneMeasureSDKDemo)
## 1.1 集成准备
#### 1.1.1  gradle 配置
在根项目的 build.gradle 文件中添加 jitpack 远程依赖：

``` 
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
``` 
在需要集成 OneMeasureSDK 的 module 的 build.gradle文件中添加如下配置：

- 添加 java8 特性支持：

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
- 添加 OneMeasureSDK 依赖：

``` 
dependencies {
    ...
    implementation "com.tozmart:tozmartSDK-s3:1.2.7"
    ...
}
``` 
#### 1.1.2 配置自定义的 Application 类
如果项目中已经有自定义的继承自 Application 的类，则将该类实现 com.jess.arms.base.App 接口并添加如下配置：

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
如果项目中没有自定义继承自 Application 的类，则需要自定义一个并添加如上配置，同时注意在 AndroidManifest 文件添加对该类的引用：

``` 
<application
    android:name=".MyApplication"
    ... />
</application>
``` 
## 1.2 接口说明
#### 1.2.1 如何使用
在需要使用OneMeasureSDK的地方添加如下代码即可调用sdk：

``` 
new ToziSDK.Builder()
        .setUserId("你的用户id")
        .setCorpId("你的企业id")
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
#### 1.2.2 参数说明

| 参数 | 类型 | 是否必填 | 接口 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| corpId | String | 是 | setCorpId(corpId)|企业账户的id，由图郅公司分配给每个企业的唯一id|
| userId | String | 是 | setUserId(userId)|企业自己分配给其用户的id，由企业自行定义|
|name|String|是|setName(name)|用户的名字|
|gender|int|是|setGender(gender)|用户的性别：Gender.MALE、Gender.FEMALE|
|height|float|是|setHeight(height)|用户的身高，单位是cm|
|weight|float|是|setWeight(weight)|用户的体重，单位是kg|
|language|int|否|setLanguage(language)|尺寸显示的语言（注意此参数并不会修改app的默认语言，只会修改尺寸展示相关的语言）：Language.ENGLISH（英语，默认）、Language.CHINESE（简体中文）、Language.TRADITION_CHINESE（繁体中文）、Language.JAPANESE（日语）|
|unit|int|否|setUnit(unit)|尺寸显示的单位：Unit.METRIC（公制，默认）、IUnit.IMPERIAL（英制）|
|isBlurFace|Boolean|否|setBlurFace(isBlurFace)|是否对拍摄的照片进行脸部模糊处理，默认是true|
|cameraMode|int|否|setCameraMode(cameraMode)|设置他拍或者自拍模式：CameraMode.SNAPSHOT（他拍模式，默认）、CameraMode.SELFIE（自拍模式）|

## 1.3 问题

如果在编译运行时遇到如下错误：
``` 
Execution failed for task ':app:transformResourcesWithMergeJavaResForDebug'.
> More than one file was found with OS independent path 'META-INF/proguard/androidx-annotations.pro'
``` 
只需在build文件中添加如下配置：
``` 
defaultConfig {
    ...
    packagingOptions {
        exclude 'META-INF/proguard/androidx-annotations.pro'
    }
}
``` 

 __Demo下载地址：__ [https://github.com/Eason2Wang/OneMeasureSDKDemo](https://github.com/Eason2Wang/OneMeasureSDKDemo)
