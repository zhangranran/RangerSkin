# RangerSkin

A framework for Android app dynamic skins

### 一、接入

SDK版本要求和注意事项：
安卓21及以上，Kotlin1.4及以上版本（项目中如果不存在Kotlin可以不用关注）。

#### 1.1 中央仓库

```
repositories {
    mavenCentral()
}
```

#### 1.2 gradle 依赖

```
dependencies {
    implementation "io.github.rangerleoio:skin-lite:1.0.0" //请使用最新版本
}
```

#### 1.3 混淆

```
# RangerSkin
-keep class com.rangerleo.skin.** { *; }
```

### 二、使用介绍

#### 2.1 初始化

```
     SkinManager.init(application)
     SkinResources.init(application)
```

使用SkinManager使用app将拥有全量自动换肤功能（同时SkinResources会在内部进行初始化），使用SkinResources仅用于户资源获取。
设计两个初始化的目的是方便开发者进行性能测试或功能调试，开发者可以根据需要二选一即可。SDK初始化操作仅仅为传递application对象对App
启动耗时无任何影响。

#### 2.3 加载和重置

```
    //加载皮肤包并立即生效
    SkinManager.instance?.applySkinStyle(skinPath)
    //重置为默认样式
    SkinManager.instance?.resetSkinStyle()
```

updateSkinStyle方法第一次加载为耗时操作，建议放在子线程中执行

#### 2.4 换肤属性支持列表

| background | src            | textColor      | textSize           |
|------------|----------------|----------------|--------------------|
| text       | hint           | textColorHint  | textCursorDrawable |
| tint       | foregroundTint | backgroundTint | style和?attr        |

如果在xml中布局中上述列表定义的属性能满足换肤，需要则接下来制作皮肤包即可,无代码修改。参考demo中[activity_main.xml](app%2Fsrc%2Fmain%2Fres%2Flayout%2Factivity_main.xml)
中`switch_btn`或者`reset_btn`即可。
另外textCursorDrawable属性在lite版本中仅支持android 10及以上。

#### 2.5 特殊场景

* 在xml布局中声明的自定义布局（参考demo中[activity_main.xml](app%2Fsrc%2Fmain%2Fres%2Flayout%2Factivity_main.xml)`demo_view`）

* 在代码中动态添加的view对象（参考demo中[MainActivity.kt](app%2Fsrc%2Fmain%2Fjava%2Fcom%2Frangerleo%2Fskin%2Fdemo%2FMainActivity.kt)`addTextView`方法）

* dialog和popupWindow（参考demo中[MainActivity.kt](app%2Fsrc%2Fmain%2Fjava%2Fcom%2Frangerleo%2Fskin%2Fdemo%2FMainActivity.kt)的`dialog`和`popupWindow`）

* fragment适配规则和activity一致，总之通过XML属性声明+View#addOnSkinChangeListener适配的方式可以满足几乎所有场景。

### 三、制作皮肤包

#### 3.1 皮肤包制作规则

制造皮肤包的规则参考[res-demo](app%2Fsrc%2Fmain%2Fres-demo)中,所有需要变更的样式的名称保持不变，修改样式内容即可。

#### 3.2 皮肤包打包脚本

参考[build.gradle](app%2Fbuild.gradle)
Demo工程下[gradle.properties](gradle.properties)

``` 
    #皮肤工程开关
    isSkinPackage=true
```

设置为true运行，执行app的assembleReslease或者assembleDebug会在[build](build)生成皮肤包。皮肤包的后缀以.skin结尾。

### 四、作者
![作者微信](styles%2Fimg.png)<br>
[作者主页](https://juejin.cn/user/1864393707749687)：https://juejin.cn/user/1864393707749687
### 五、协议
RangerSkin is licensed under the Apache License 2.0: LICENSE.



