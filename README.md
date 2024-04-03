# ElasticViews 

<p align="center">
  <a href="https://opensource.org/licenses/MIT"><img alt="License" src="https://img.shields.io/badge/license-MIT%20License-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=16"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://travis-ci.org/skydoves/ElasticViews"><img alt="Build Status" src="https://travis-ci.org/skydoves/ElasticViews.svg?branch=master"/></a>
  <a href="https://androidweekly.net/issues/issue-336"><img alt="Android Weekly" src="https://img.shields.io/badge/Android%20Weekly-%23336-orange.svg"/></a>
  <a href="https://skydoves.github.io/libraries/elasticviews/javadoc/elasticviews/com.skydoves.elasticviews/index.html"><img alt="Javadoc" src="https://img.shields.io/badge/Javadoc-ElasticViews-yellow"/></a>
</p>

<p align="center">
✨ An easy way to implement an elastic touch effect for Android.
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/72123075-73943500-33a3-11ea-883f-9009de998788.gif" width="32%"/>
<img src="https://user-images.githubusercontent.com/24237865/72123076-73943500-33a3-11ea-92ef-0924cd0b902e.gif" width="32%"/>
</p>

## Including in your project
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/elasticviews.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.skydoves%22%20AND%20a:%22elasticviews%22)
[![Kitpack](https://jitpack.io/v/skydoves/ElasticViews.svg)](https://jitpack.io/#skydoves/ElasticViews)

#### Gradle
Add codes below to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        mavenCentral()
    }
}
```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:elasticviews:2.1.0"
}
```
## SNAPSHOT 
[![ElasticViews](https://img.shields.io/static/v1?label=snapshot&message=elasticviews&logo=apache%20maven&color=C71A36)](https://oss.sonatype.org/content/repositories/snapshots/com/github/skydoves/elasticviews/) <br>
Snapshots of the current development version of ElasticViews are available, which track [the latest versions](https://oss.sonatype.org/content/repositories/snapshots/com/github/skydoves/elasticviews/).
```Gradle
repositories {
   maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
}
```

## Usage
`ElasticViews` lets we use like using normal views and gives all of the Views or GroupViews touch effect very simply.

#### Add XML Namespace
First add below XML Namespace inside your XML layout file.

```gradle
xmlns:app="http://schemas.android.com/apk/res-auto"
```

#### OnClick Method
All of ElasticViews should be set `OnClickListener` or `onClick` method. If not, nothing happens.
```java
ElasticButton elasticButton = (ElasticButton)findViewById(R.id.elasticbutton);
elasticButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // do something
    }
});
```

### ElasticButton
```gradle
<com.skydoves.elasticviews.ElasticButton
   android:layout_width="match_parent"
   android:layout_height="wrap_content"
   android:text="Elastic Button"
   android:textColor="@android:color/white"
   android:textSize="17sp"
   app:button_cornerRadius="4dp"
   app:button_duration="250"
   app:button_scale="0.87" />
```

### ElasticCheckButton
```gradle
<com.skydoves.elasticviews.ElasticCheckButton
   android:layout_width="match_parent"
   android:layout_height="45dp"
   android:background="#30354b"
   android:text="Text"
   android:textColor="@android:color/white"
   android:textStyle="bold"
   app:checkButton_cornerRadius="4dp"
   app:checkButton_alpha="0.7"
   app:checkButton_duration="400"
   app:checkButton_scale="0.9" />
```

### ElasticImageView
```gradle
<com.skydoves.elasticviews.ElasticImageView
   android:layout_width="23dp"
   android:layout_height="23dp"
   android:scaleType="fitXY"
   android:src="@drawable/ic_question"
   android:tint="#3d95c9"
   app:imageView_scale="0.7"
   app:imageView_duration="300" />
```

### ElasticFloatingActionButton
```gradle
<com.skydoves.elasticviews.ElasticFloatingActionButton
   android:layout_width="64dp"
   android:layout_height="64dp"
   android:src="@drawable/ic_add"
   android:tint="#ffffff"
   app:fabSize="normal"
   app:fabutton_duration="400"
   app:fabutton_scale="0.85" />
```

### ElasticCardView
```gradle
<com.skydoves.elasticviews.ElasticCardView
  android:layout_width="match_parent"
  android:layout_height="120dp"
  app:cardCornerRadius="8dp"
  app:cardElevation="12dp"
  app:cardBackgroundColor="@color/background"
  app:cardView_duration="250"
  app:cardView_scale="0.8" >

  ...

</com.skydoves.elasticviews.ElasticCardView>
```

### ElasticLayout
ElasticLayout gives elastic animation to all child views.

```gradle
<com.skydoves.elasticviews.ElasticLayout
  android:layout_width="match_parent"
  android:layout_height="80dp"
  app:layout_cornerRadius="4dp"
  app:layout_duration="500"
  app:layout_scale="0.85">

  <TextView
      android:id="@+id/textView0"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="This is"
      android:textColor="#ffffff"
      android:textSize="18sp" />

  <TextView
      android:layout_below="@+id/textView1"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:layout_alignParentBottom="true"
      android:text="ElasticLayout"
      android:textColor="#ffffff"
      android:textSize="18sp"
      android:gravity="end" />
</com.skydoves.elasticviews.ElasticLayout>
```

### ElasticAnimation
ElasticAnimation implements elastic animations for android views and view groups. <br>
```java
new ElasticAnimation(clickedView).setScaleX(0.9f).setScaleY(0.9f).setDuration(400)
.setOnFinishListener(onFinishListener).doAction();
```

<img src="https://user-images.githubusercontent.com/24237865/72123077-742ccb80-33a3-11ea-9262-c4977983247e.gif" align="right" width="30%">

#### ViewPropertyAnimatorListener
we can set `ViewPropertyAnimatorListener` using `setListener` method and detect animation's status.
```java
.setListener(new ViewPropertyAnimatorListener() {
   @Override
   public void onAnimationStart(View view) {
       // do something
   }

   Override
   public void onAnimationEnd(View view) {
       finishListener.onFinished();
   }

   Override
   public void onAnimationCancel(View view) {
       // do something
   }
});
```

#### Kotlin Extension
ElasticAnimation supports kotlin extension `elasticAnimation`.
```kotlin
val anim = textView.elasticAnimation(0.8f, 0.8f, 400, object: ElasticFinishListener {
    override fun onFinished() {
        // do anything
    }
})
anim.doAction()
```

#### Kotlin dsl
```kotlin
elasticAnimation(this) {
  setDuration(duration)
  setScaleX(scale)
  setScaleY(scale)
  setOnFinishListener(object : ElasticFinishListener {
      override fun onFinished() {
           onClick()
      }
  })
}.doAction()
```

#### Example : Normal Button
we can implement animation on all of the views like below.
```java
@OnClick(R.id.button)
public void addNewAlarm(View v){
    // implements animation uising ElasticAnimation
    new ElasticAnimation(v).setScaleX(0.85f).setScaleY(0.85f).setDuration(500)
    .setOnFinishListener(new ElasticFinishListener() {
            @Override
            public void onFinished() {
                // Do something after duration time
            }
        }).doAction();
    }
}
```

#### Example : ListView Item
So also we can implement animation on listView's items like below.
```java
private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View clickedView, final int pos, long id) {
        new ElasticAnimation(clickedView).setScaleX(0.9f).setScaleY(0.9f).setDuration(400)
        .setOnFinishListener(new ElasticFinishListener() {
              @Override
              public void onFinished() {
                  // Do something after duration time
                  Toast.makeText(getBaseContext(), "ListViewItem" + pos, Toast.LENGTH_SHORT).show();
              }
          }).doAction();
        }
    };
```

## Find this library useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/ElasticViews/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/skydoves)__ me for my next creations! 🤩

# License
```xml
The MIT License (MIT)

Copyright (c) 2017 skydoves

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
