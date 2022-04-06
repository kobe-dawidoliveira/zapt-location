package com.example.zapt_location;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import tech.zapt.sdk.location.ZaptSDK;

/** ZaptLocationPlugin */
public class ZaptLocationPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private ZaptSDK zaptSDK;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "zapt_location");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if(call.method.equals("initializeZapt")) {
      String placeId = call.argument('placeID').toString();
      initializeZapt(placeId);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  private void initializeZapt(@NonNull String placeId) {
    zaptSDK = ZaptSDK.getInstance(this);
    zaptSDK.requestPermissions(this);
    zaptSDK.verifyBluetooth(null, null);
    //Se tem mapa integrado
    webView = (WebView) findViewById(R.id.webView);
    zaptSDK.setWebViewClient(webView);
    
    if(!zaptSDK.isInitialized()){
      zaptSDK.initialize(placeId);
    }
  }
}
