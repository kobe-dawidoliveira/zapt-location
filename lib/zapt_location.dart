import 'dart:async';

import 'package:flutter/services.dart';

class ZaptLocation {
  static const MethodChannel _channel = MethodChannel('zapt_location');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> initialize({required String placeID}) async {
    await _channel.invokeMethod('initializeZapt', {
      'placeId': placeID,
    });
  }
}
