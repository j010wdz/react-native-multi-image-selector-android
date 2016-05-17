package com.j010wdz.multiimageselector;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class RNMultiImageSelectorModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final String E_NO_IMAGE_SELECTED = "E_NO_IMAGE_SELECTED";

    private static final int REQUEST_IMAGE = 2;
    private static final int REQUEST_GOODS_LOGO = 3; //选取商品Logo图片

    private ReactApplicationContext reactContext;
    private Promise promise;

    public RNMultiImageSelectorModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.reactContext.addActivityEventListener(this);
    }

    @Override
    public String getName() {
      return "MultiImageSelectorAndroid";
    }

    @ReactMethod
    protected void selectPictures(final int action, int max_select_count, Promise promise) {

        this.promise = promise;

        //打开图片选择窗口
        final Intent intent = new Intent(this.reactContext, MultiImageSelectorActivity.class);
        // whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, max_select_count);
        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        // default select images (support array list)
        //intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaultDataArray);
        this.reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                reactContext.startActivityForResult(intent, action, null);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) { //RESULT_OK

            // Get the result list of select image paths
            List<String> pathList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            // do your logic ....
            if (pathList.isEmpty()) {
                this.promise.reject(E_NO_IMAGE_SELECTED, "未选择图片");
                return;
            }

            WritableNativeArray pathArray = new WritableNativeArray();
            for (String path : pathList) {
                pathArray.pushString(path);
            }
            this.promise.resolve(pathArray);
        } else {
            this.promise.reject(E_NO_IMAGE_SELECTED, "未选择图片");
        }
    }
}
