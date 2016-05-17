# react-native-multi-image-selector-android
本项目为React Native的Android原生模块，是 https://github.com/lovetuzitong/MultiImageSelector 项目的react-native封装模块，用于打开activity选择多张图片。

[![npm version](http://img.shields.io/npm/v/react-native-multi-image-selector-android.svg?style=flat-square)](https://npmjs.org/package/react-native-multi-image-selector-android "View this project on npm")
[![npm downloads](http://img.shields.io/npm/dm/react-native-multi-image-selector-android.svg?style=flat-square)](https://npmjs.org/package/react-native-multi-image-selector-android "View this project on npm")
[![npm licence](http://img.shields.io/npm/l/react-native-multi-image-selector-android.svg?style=flat-square)](https://npmjs.org/package/react-native-multi-image-selector-android "View this project on npm")


使用本模块用于选择多张图片

### 安装

```bash
npm install react-native-multi-image-selector-android --save
```

### 添加到你的android项目

* 在 `android/setting.gradle` 文件中添加以下内容

```gradle
...
include ':RNMultiImageSelectorModule', ':app'
project(':RNMultiImageSelectorModule').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-multi-image-selector-android')
```

* 在 `android/app/build.gradle` 文件中添加如下内容

```gradle
...
dependencies {
    ...
    compile project(':RNMultiImageSelectorModule')
}
```

* 注册模块 >= 0.18 (在 MainActivity.java 文件中添加内容)

```java
import com.j010wdz.multiimageselector.RNMultiImageSelectorPackage;  // <--- import

public class MainActivity extends ReactActivity {
  ......

  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new RNMultiImageSelectorPackage()); // <------ 添加这行代码到你的MainActivity类
  }

  ......

}
```

* 注册模块 <= 0.17 (在 MainActivity.java 文件中添加内容)

```java
import com.j010wdz.multiimageselector.RNMultiImageSelectorPackage;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new RNMultiImageSelectorPackage()) // <------ 添加这行代码到你的MainActivity类
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRNSample", null);

    setContentView(mReactRootView);
  }

  ......

}
```

## 示例 打开分享微信朋友圈编辑窗口
```javascript
var MultiImageSelectorAndroid = require('react-native-multi-image-selector-android');

MultiImageSelectorAndroid.selectPictures(1, 9).then((pathArray)=>{
      for (var path of pathArray) {
        alert(path);
      }
    }, (code, message)=>{
      //alert(code);
    });
```

## License
MIT
