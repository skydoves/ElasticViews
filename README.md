# ElasticViews 
![license](https://img.shields.io/badge/license-MIT%20License-blue.svg)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![Build Status](https://travis-ci.org/skydoves/ElasticViews.svg?branch=master)](https://travis-ci.org/skydoves/ElasticViews)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23336-orange.svg)](https://androidweekly.net/issues/issue-336) <br>

A library that lets you implement elastic touch animation easily.

![gif0](https://user-images.githubusercontent.com/24237865/48674497-53e2f180-eb90-11e8-86fb-476f841b3e32.gif)
![gif1](https://user-images.githubusercontent.com/24237865/48674498-53e2f180-eb90-11e8-8313-aa94b34a40d2.gif)

## Including in your project
#### build.gradle
Add below codes to your root `build.gradle` file (not your module build.gradle file).
```gradle
repositories {
    jcenter()
}

dependencies {
    implementation "com.github.skydoves:elasticviews:2.0.1"
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
All of ElasticViews should be set OnClickListener or OnClick Method, etc. If not, nothing happens.
```java
ElasticButton elasticButton = (ElasticButton)findViewById(R.id.elasticbutton);
elasticButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // do something
    }
});
```

or use butterknife
```java
@OnClick(R.id.elasticbutton)
public void onClick(View v) {
    // do something
} 
```

### ElasticButton
```gradle
<com.skydoves.elasticviews.ElasticButton
        android:id="@+id/elasticbutton"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        app:button_backgroundColor="#30354b"
        app:button_round="20"
        app:button_scale="0.7"
        app:button_duration="400"
        app:button_labelText="Elastic Button"
        app:button_labelColor="#ffffff"
        app:button_labelSize="16"
        app:button_labelStyle="bold"/>
```

### ElasticButton use like TextView
If `button_backgroundColor` attribute be set as `@android:color/transparent`, it will looks like a TextView. <br>
```gradle
app:button_backgroundColor="@android:color/transparent"
```

### ElasticCheckButton
```gradle
<com.skydoves.elasticviews.ElasticCheckButton
        android:id="@+id/elasticcheckbutton"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        app:checkbutton_backgroundColor="#30354b"
        app:checkbutton_round="30"
        app:checkbutton_scale="0.9"
        app:checkbutton_duration="400"
        app:checkbutton_labelText="Elastic CheckButton"
        app:checkbutton_labelColor="#ffffff"
        app:checkbutton_labelSize="16"
        app:checkbutton_labelStyle="bold"
        app:checkbutton_alpha="0.5"
        app:checkbutton_ischecked="false"/>
```

### ElasticImageView
```gradle
<com.skydoves.elasticviews.ElasticImageView
            android:id="@+id/elasticimageview"
            android:layout_width="64dp"
            android:layout_height="64dp"
            android:scaleType="fitXY"
            android:src="@drawable/ic_question"
            app:imageview_duration="500"
            app:imageview_scale="0.7"/>
```

### ElasticFloatingButton
```gradle
<com.skydoves.elasticviews.ElasticFloatingActionButton
            android:id="@+id/elasticfab"
            android:layout_width="64dp"
            android:layout_height="64dp"
            android:src="@drawable/ic_add"
            app:fabSize="normal"
            app:fabutton_duration="400"
            app:fabutton_scale="0.85"/>
```

### ElasticLayout
ElasticLayout gives elastic animation to all child views.

```gradle
<com.skydoves.elasticviews.ElasticLayout
        android:id="@+id/elasticlayout"
        android:layout_width="match_parent"
        android:layout_height="80dp"
        app:layout_backgroundColor="#30354b"
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

### ElasticAnmimation
ElasticAnimation lets we can implement elastic animation on all of the views. <br>
```java
new ElasticAnimation(clickedView).setScaleX(0.9f).setScaleY(0.9f).setDuration(400)
.setOnFinishListener(onFinishListener).doAction();
```
or we can set ViewPropertyAnimatorListener using setListener method and detect animation start and end.
```java
.setListener(new ViewPropertyAnimatorListener() {
          @Override
          public void onAnimationStart(View view) {
          }

          @Override
          public void onAnimationEnd(View view) {
              finishListener.onFinished();
          }

          @Override
          public void onAnimationCancel(View view) {
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
                            //Do something after duration time
                            Toast.makeText(getBaseContext(), "ListViewItem" + pos, Toast.LENGTH_SHORT).show();
                        }
                    }).doAction();
        }
    };
```

#### ElasticAnimation Preview
![gif2](https://cloud.githubusercontent.com/assets/24237865/22189011/14bc94aa-e15d-11e6-9078-2dfc9d97ef87.gif)

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
